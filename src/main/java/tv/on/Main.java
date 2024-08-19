package tv.on;


import tv.on.downloaders.Scrapper;
import tv.on.downloaders.Telemagazyn;
import tv.on.models.Channel;
import tv.on.models.TVShow;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Scrapper scapperProvider = new Telemagazyn();
        List<Channel> channels = scapperProvider.getAllChannels();

//        HashMap<Channel, HashMap<LocalDate, List<TVShow>>> schedule = new HashMap<>();
//
//        for (Channel channel: channels) {
//            HashMap<LocalDate, List<TVShow>>channelSchedule = new HashMap<>();
//            LocalDate today = LocalDate.now();
//            for (int i=1; i <= Telemagazyn.NO_DAYS_IN_FUTURE; i++){
//                List<TVShow> tvShowsForDate = scapperProvider.getChannelScheduleForDate(today, channel);
//                channelSchedule.put(today, tvShowsForDate);
//                today = today.plusDays(1);
//            }
//            schedule.put(channel, channelSchedule);
//        }
    }

}