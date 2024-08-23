package tv.on.Repositories;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.on.models.TVShow;

import java.time.LocalDateTime;
import java.util.List;

public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    List<TVShow> findByTitle(String title);
    List<TVShow> findByTitleAndStartDateTimeAfter(String title, LocalDateTime startDateTime);
    List<TVShow> findByTitleAndStartDateTimeAfterAndChannelCountryCode(String title, LocalDateTime startDateTime, CountryCode countryCode);
}
