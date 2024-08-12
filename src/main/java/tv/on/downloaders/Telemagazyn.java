package tv.on.downloaders;


import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.on.models.Channel;
import tv.on.models.TVShow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Telemagazyn extends ScrapperBase implements Scrapper {

    private static final Logger logger = LoggerFactory.getLogger(Telemagazyn.class);

    public static final String URL = "https://telemagazyn.pl";
    public static final String CHANNELS_URL = URL + "/stacje";
    public static final Integer NO_DAYS_IN_FUTURE = 6;

    @Override
    public List<Channel> getAllChannels() {
        Document doc = downloadHtml(CHANNELS_URL);
        Elements elements = doc.selectXpath("//a[@class='atomsTvChannelList__link']");
        return elements.stream()
                .map(elem -> {
                    Channel channel = new Channel();
                    channel.name = elem.text();
                    channel.url = elem.attribute("href")
                            .toString()
                            .replace("href=\"", "")
                            .replace("\"", "");
                    return channel;
                })
                .toList();
    }

    @Override
    public List<TVShow> getChannelScheduleForDate(LocalDate date, Channel channel) {
        Document doc = downloadHtml(channel.url + "?dzien=" + date.toString());
        Elements elements = doc.selectXpath("//li[contains(@class, 'componentsTvChannelTvGuide__item')]");
        return elements.stream()
                        .map(html -> {
                            TVShow tvShow = new TVShow();
                            tvShow.channel = channel.name;
                            tvShow.lead = html.getElementsByClass("atomsTvChannelEmissionTile__lead").text();
                            tvShow.title = html.getElementsByClass("atomsTvChannelEmissionTile__title").text();
                            tvShow.startTime = LocalDateTime.parse(html.getElementsByTag("time").getFirst().attributes().get("data-time"), DateTimeFormatter.ISO_ZONED_DATE_TIME);
                            tvShow.endDateTime = LocalDateTime.parse(html.getElementsByTag("time").getFirst().attributes().get("data-endtime"), DateTimeFormatter.ISO_ZONED_DATE_TIME);
                            logger.info("Found TV Show: {}", tvShow);
                            return tvShow;
                        })
                .toList();
    }

}
