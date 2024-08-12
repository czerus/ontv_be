package tv.on.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TVShow {
    public String title;
    public String lead;
    public String originalTitle;
    public String lengthInMin;
    public String director;
    public String channel;
    public LocalDateTime startTime;
    public LocalDateTime endDateTime;
}
