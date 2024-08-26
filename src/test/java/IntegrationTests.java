import com.neovisionaries.i18n.CountryCode;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tv.on.OnTvApplication;
import tv.on.Repositories.ChannelRepository;
import tv.on.Repositories.HistoryRepository;
import tv.on.Repositories.TVShowRepository;
import tv.on.models.Channel;
import tv.on.models.History;
import tv.on.models.TVShow;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = OnTvApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class IntegrationTests {

    private final LocalDate updateTime = LocalDate.now();
    private final LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private final LocalDateTime endTime = startTime.plus(Duration.ofMinutes(30L));
    private final Channel channel = new Channel(
            1L,
            "Channel Name",
            "https://channel.url",
            CountryCode.getByAlpha2Code("PL"));

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private TVShowRepository tvShowRepository;

    @Autowired
    private HistoryRepository historyRepository;


    @SneakyThrows
    @Test
    public void testCreateChannel() {
        createTestChannel();

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/channel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Channel Name"))
                .andExpect(jsonPath("$[0].url").value("https://channel.url"))
                .andExpect(jsonPath("$[0].countryCode").value("PL"));
    }

    @SneakyThrows
    @Test
    public void testCreateTVShow() {
        createTestTVShow();

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/tvshow?title=TVShowTitle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("TVShowTitle"))
                .andExpect(jsonPath("$[0].lead").value("lorem ipsum dolor"))
                .andExpect(jsonPath("$[0].episode").value("11"))
                .andExpect(jsonPath("$[0].originalTitle").value("org title"))
                .andExpect(jsonPath("$[0].lengthInMin").value("120"))
                .andExpect(jsonPath("$[0].director").value("Tim Miller"))
                .andExpect(jsonPath("$[0].channel").value(this.channel))
                .andExpect(isDateTimeMatching("startDateTime", this.startTime))
                .andExpect(isDateTimeMatching("endDateTime", this.endTime));
    }

    @SneakyThrows
    @Test
    public void testCreateHistory() {
        createTestHistory();

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/updatetime?countryCode=PL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.latestDateWithSchedule").value(updateTime.toString()));


    }

    private void createTestHistory() {
        List<History> history = List.of(
                new History(
                        1L, 
                        CountryCode.getByAlpha2Code("PL"),
                        LocalDate.now().minusDays(1),
                        LocalDateTime.now(), 
                        LocalDateTime.now().plus(Duration.ofMinutes(5))
                ),
                new History(
                        2L, 
                        CountryCode.getByAlpha2Code("PL"),
                        updateTime,
                        LocalDateTime.now(), 
                        LocalDateTime.now().plus(Duration.ofMinutes(5))
                )
        );
        historyRepository.saveAll(history);
    }

    private void createTestTVShow() {
        channelRepository.save(this.channel);
        tvShowRepository.save(
                new TVShow(1L,
                        "TVShowTitle",
                        "lorem ipsum dolor",
                        "11",
                        "org title",
                        "120",
                        "Tim Miller",
                        this.channel,
                        this.startTime,
                        this.endTime));
    }

    private void createTestChannel() {
        channelRepository.save(this.channel);
    }

    @SneakyThrows
    public static ResultMatcher isDateTimeMatching(String jsonTimeKey,LocalDateTime expectedDateTime) {
        return mvcResult -> {
            String contentAsString = mvcResult.getResponse().getContentAsString();
            JSONObject js = new JSONObject(contentAsString.replace("[", "").replace("]", ""));
            if (!js.get(jsonTimeKey).equals(expectedDateTime.toString())) {
                throw new AssertionError("");
            }
        };
    }
}
