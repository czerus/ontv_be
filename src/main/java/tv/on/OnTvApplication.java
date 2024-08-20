package tv.on;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tv.on.Repositories.ChannelRepository;
import tv.on.Repositories.TVShowRepository;
import tv.on.downloaders.Scrapper;
import tv.on.downloaders.Telemagazyn;
import tv.on.models.Channel;
import tv.on.models.TVShow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEncryptableProperties
public class OnTvApplication implements CommandLineRunner {

	@Autowired
	ChannelRepository channelRepository;
	@Autowired
	TVShowRepository tvShowRepository;

	public static void main(String[] args) {

		SpringApplication.run(OnTvApplication.class, args);

	}

	@Override
	public void run(String... args) {

		Scrapper scrapperProvider = new Telemagazyn();
		List<Channel> channels = scrapperProvider.getAllChannels();
		channelRepository.saveAll(channels);

		List<TVShow> schedule = new ArrayList<>();
        for (Channel channel: channels) {
			LocalDate today = LocalDate.now();
			for (int i = 1; i <= Telemagazyn.NO_DAYS_IN_FUTURE; i++) {
				List<TVShow> tvShowsForDate = scrapperProvider.getChannelScheduleForDate(today, channel);
				schedule.addAll(tvShowsForDate);
				today = today.plusDays(1);
				break;
			}
			break;
		}
		tvShowRepository.saveAll(schedule);
	}

}
