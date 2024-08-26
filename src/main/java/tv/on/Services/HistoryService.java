package tv.on.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;

import tv.on.Repositories.HistoryRepository;
import tv.on.models.History;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History getLatestScrappingForCountry(CountryCode countryCode) {
        return historyRepository.findByCountryCodeOrderByLatestDateWithScheduleDesc(countryCode).get(0);
    }
    
}
