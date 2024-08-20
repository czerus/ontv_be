package tv.on.Services;

import com.neovisionaries.i18n.CountryCode;
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

    public List<TVShow> getTVShowsWithTitleFromTime(String title, String date) {;
        return tvShowRepository.findByTitleAndStartTimeAfter(title, stringToDateTime(date));
    }

    public List<TVShow> getTVSowsWithTitleFromDateForCountry(String title, String date, String country) {
        return tvShowRepository.findByTitleAndStartTimeAfterAndChannelCountryCode(
                title,
                stringToDateTime(date),
                CountryCode.getByAlpha2Code(country)
        );
    }

    private LocalDateTime stringToDateTime(String dateString) {
        List<String> dateParts = Arrays.asList(dateString.split("-"));
        return LocalDateTime.of(
                Integer.parseInt(dateParts.get(0)),
                Integer.parseInt(dateParts.get(1)),
                Integer.parseInt(dateParts.get(2)), 0, 0);
    }
}
