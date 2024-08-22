package tv.on;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnTvApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(OnTvApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(OnTvApplication.class, args);
	}

	@Override
	@SneakyThrows
	public void run(String... args) {
		logger.info("Starting the onTV app");
	}

}
