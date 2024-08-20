package tv.on.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tv.on.downloaders.ScrapperRunner;

import java.text.SimpleDateFormat;
import java.util.Set;

import static tv.on.Utils.findAllScrapperProviderClasses;

@Component
public class ScrapperScheduler {

	@Autowired
	ScrapperRunner scrapperRunner;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


//	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	@Scheduled(cron = "0 23 * * *")
    public void scheduleScrappingByProviders() {

		Set<Class> scrapperProviders = findAllScrapperProviderClasses("tv.on.downloaders");
		scrapperProviders.forEach(
						clazz ->this.scrapperRunner.startScraping(clazz)
				);
	}
}