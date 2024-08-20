package tv.on.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tv.on.Repositories.TVShowRepository;
import tv.on.models.TVShow;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TVShowService {

    private final TVShowRepository tvShowRepository;

    @Autowired
    public TVShowService(TVShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public List<TVShow> getTVShowsWithTitle(String title) {
        return tvShowRepository.findByTitle(title);
    }

    public List<TVShow> getTVShowsWithTitleFromTime(String title, String date) {
        List<String> dateParts = Arrays.asList(date.split("-"));
        LocalDateTime dt = LocalDateTime.of(
                Integer.parseInt(dateParts.get(0)),
                Integer.parseInt(dateParts.get(1)),
                Integer.parseInt(dateParts.get(2)), 0, 0);
        return tvShowRepository.findByTitleAndStartTimeAfter(title, dt);
    }

}
