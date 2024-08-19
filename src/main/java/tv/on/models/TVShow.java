package tv.on.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class TVShow {
    @Id
    @SequenceGenerator(
            name = "channel_sequence",
            sequenceName = "channel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "channel_sequence"
    )
    public Long id;
    public String title;
    @Column(length=500)
    public String lead;
    public String episode;
    public String originalTitle;
    public String lengthInMin;
    public String director;
    @ManyToOne
    public Channel channel;
    public LocalDateTime startTime;
    public LocalDateTime endDateTime;
}
