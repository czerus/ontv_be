package tv.on.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neovisionaries.i18n.CountryCode;

import tv.on.Services.HistoryService;
import tv.on.models.History;

@RestController
@RequestMapping(path="api/v1/")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("updatetime")
    public History getLatestUpdateForCountry(CountryCode countryCode) {
        return historyService.getLatestScrappingForCountry(countryCode);
    }
    
}
