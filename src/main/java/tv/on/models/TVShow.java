package tv.on.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class TVShow {
    @Id
    @SequenceGenerator(
            name = "tvshow_sequence",
            sequenceName = "tvshow_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tvshow_sequence"
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
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;

}
