package tv.on.Repositories;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tv.on.models.History;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByCountryCodeOrderByLatestDateWithScheduleDesc(CountryCode countryCode);
}
