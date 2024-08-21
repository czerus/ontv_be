package tv.on.downloaders;

import com.neovisionaries.i18n.CountryCode;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tv.on.Repositories.ChannelRepository;
import tv.on.Repositories.HistoryRepository;
import tv.on.Repositories.TVShowRepository;
import tv.on.Utils;
import tv.on.models.Channel;
import tv.on.models.History;
import tv.on.models.TVShow;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;


@Component
public class ScrapperRunner {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    TVShowRepository tvShowRepository;
	@Autowired
	HistoryRepository historyRepository;

	@SneakyThrows
    public void startScraping(Class<?> scrapperClass) {
		Scrapper sc = (Scrapper) scrapperClass.getDeclaredConstructor().newInstance();
    	Integer noOfDaysInFuture = Utils.getIntFieldValueByReflection(scrapperClass,"NO_DAYS_IN_FUTURE");
		CountryCode countryCode = Utils.getCountryCodeFieldValueByReflection(scrapperClass, "COUNTRY_CODE");

		List<Channel> channels = sc.getAllChannels();
		channelRepository.saveAll(channels);

		List<TVShow> schedule = new ArrayList<>();
		LocalDateTime startTime = LocalDateTime.now();
		LocalDate date =  getDateForStartingScrapping(countryCode);
		LocalDate today = LocalDate.now();
		int difference = date.getDayOfYear() - today.getDayOfYear();
		int noOfDaysToRetrieveSchedule = noOfDaysInFuture - difference;

		for (Channel channel: channels) {
			for (int i = 1; i <= noOfDaysToRetrieveSchedule; i++) {
				List<TVShow> tvShowsForDate = sc.getChannelScheduleForDate(date, channel);
				schedule.addAll(tvShowsForDate);
				date = date.plusDays(1);
			}
		}
		LocalDateTime endTime = LocalDateTime.now();
		historyRepository.save(new History(countryCode, date, startTime, endTime));
		tvShowRepository.saveAll(schedule);
    }

	private LocalDate getDateForStartingScrapping(CountryCode countryCode) {
		LocalDate date = LocalDate.now();
		List<History> historyList = historyRepository.findByCountryCodeOrderByLatestDateWithSchedule(countryCode);
		if (historyList.isEmpty()) {
			return date;
		}
        return historyList.get(0).getLatestDateWithSchedule();
	}
}
