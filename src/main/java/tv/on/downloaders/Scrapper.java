package tv.on.downloaders;

import tv.on.models.Channel;
import tv.on.models.TVShow;

import java.time.LocalDate;
import java.util.List;

public interface Scrapper {

    List<Channel> getAllChannels();
    List<TVShow> getChannelScheduleForDate(LocalDate date, Channel channel);

}
