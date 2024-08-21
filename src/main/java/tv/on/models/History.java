package tv.on.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @SequenceGenerator(
            name = "_sequence",
            sequenceName = "history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "history_sequence"
    )
    public Long id;
    @NonNull
    public CountryCode countryCode;
    @NonNull
    public LocalDate latestDateWithSchedule;
    @NonNull
    public LocalDateTime latestScrappingStartTime;
    @NonNull
    public LocalDateTime latestScrappingEndTime;
}
