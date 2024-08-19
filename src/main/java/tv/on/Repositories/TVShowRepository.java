package tv.on.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.on.models.TVShow;

public interface TVShowRepository extends JpaRepository<TVShow, Long> {
}
