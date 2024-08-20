package tv.on.Repositories;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tv.on.models.TVShow;

import java.time.LocalDateTime;
import java.util.List;

public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    List<TVShow> findByTitle(String title);
    List<TVShow> findByTitleAndStartTimeAfter(String title, LocalDateTime startTime);
//    @Query("select * from tvshow where xex.id = ?1 and ex.status in (?2)")
//    List<TVShow> findByTitleAndStartTimeAfterWhereCountry(String title, LocalDateTime startTime, CountryCode countryCode);
    List<TVShow> findByTitleAndStartTimeAfterAndChannelCountryCode(String title, LocalDateTime startTime, CountryCode countryCode);
}
