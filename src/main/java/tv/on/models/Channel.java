package tv.on.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Channel {
    public String name;
    public String url;

    public  Channel() {

    }

    public  Channel(String name, String url) {
        this.name  = name;
        this.url = url;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    private Channel(ChannelBuilder builder) {
        this.name = builder.name;
        this.url = builder.url;
    }

    public class ChannelBuilder {

        public String name;
        public String url;

        public ChannelBuilder(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public ChannelBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Channel build() {
            return new Channel(this);
        }
    }


}
