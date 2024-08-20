package tv.on.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.on.Services.TVShowService;
import tv.on.models.TVShow;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class TVShowController {

    private final TVShowService tvShowService;

    @Autowired
    public TVShowController(TVShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping("tvshow")
    public List<TVShow> getTVShowsWithTitle(String title) {
        return  tvShowService.getTVShowsWithTitle(title);
    }

    @GetMapping("tvshowfrom")
    public List<TVShow> getTVShowsWithTitleFromDate(String title, String fromDate) {
        return  tvShowService.getTVShowsWithTitleFromTime(title, fromDate);
    }



}
