package tv.on.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.on.Services.ChannelService;
import tv.on.models.Channel;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/channel")
public class ChannelController {

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public List<Channel> getChannels() {
        return  channelService.getChannels();
    }


}
