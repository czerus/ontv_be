package tv.on;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tv.on.Repositories.ChannelRepository;
import tv.on.Repositories.TVShowRepository;

@SpringBootApplication
@EnableEncryptableProperties
@EnableScheduling
public class OnTvApplication implements CommandLineRunner {

//	@Autowired
//	ChannelRepository channelRepository;
//	@Autowired
//	TVShowRepository tvShowRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnTvApplication.class, args);
	}

	@Override
	@SneakyThrows
	public void run(String... args) {
		System.out.println("Starting app");
	}

}
