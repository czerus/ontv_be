package tv.on.downloaders;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tv.on.Repositories.ChannelRepository;
import tv.on.Repositories.TVShowRepository;
import tv.on.models.Channel;
import tv.on.models.TVShow;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class ScrapperRunner {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    TVShowRepository tvShowRepository;

	@SneakyThrows
    public void startScraping(Class<?> scrapperClass) {
		Scrapper sc = (Scrapper) scrapperClass.getDeclaredConstructor().newInstance();
		Field field = scrapperClass.getDeclaredField("NO_DAYS_IN_FUTURE");
    	Integer noOfDaysInFuture = (Integer) field.get(null);

		List<Channel> channels = sc.getAllChannels();
		channelRepository.saveAll(channels);

		List<TVShow> schedule = new ArrayList<>();
        for (Channel channel: channels) {
			LocalDate today = LocalDate.now();
			for (int i = 1; i <= noOfDaysInFuture; i++) {
				List<TVShow> tvShowsForDate = sc.getChannelScheduleForDate(today, channel);
				schedule.addAll(tvShowsForDate);
				today = today.plusDays(1);
			}
		}
		tvShowRepository.saveAll(schedule);
    }
}
