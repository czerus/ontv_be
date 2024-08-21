package tv.on.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tv.on.downloaders.ScrapperRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Set;

import static tv.on.Utils.findAllScrapperProviderClasses;

@Component
public class ScrapperScheduler {

	private static final Logger logger = LoggerFactory.getLogger(ScrapperScheduler.class);

	@Autowired
	ScrapperRunner scrapperRunner;

	@Scheduled(cron = "0 0 23 * * * ")
    public void scheduleScrappingByProviders() {

		Set<Class> scrapperProviders = findAllScrapperProviderClasses("tv.on.downloaders");
        logger.info("Started TV schedules scrapping at {}", LocalDateTime.now());
		scrapperProviders.forEach(
						clazz ->this.scrapperRunner.startScraping(clazz)
				);
        logger.info("Finished TV schedules scrapping at {}", LocalDateTime.now());
    }
}