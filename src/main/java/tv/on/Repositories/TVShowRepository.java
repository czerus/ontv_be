package tv.on.Repositories;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.on.models.TVShow;

import java.time.LocalDateTime;
import java.util.List;

public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    List<TVShow> findByTitle(String title);
    List<TVShow> findByTitleAndStartTimeAfter(String title, LocalDateTime startTime);
    List<TVShow> findByTitleAndStartTimeAfterAndChannelCountryCode(String title, LocalDateTime startTime, CountryCode countryCode);
}
