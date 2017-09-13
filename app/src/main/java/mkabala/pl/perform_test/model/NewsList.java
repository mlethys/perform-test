package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by mkabala on 12.09.2017.
 */

@Root(name = "rss", strict = false)
public class NewsList {

    @Element(name = "channel")
    public Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Root(name = "channel", strict = false)
    public static class Channel {
        @ElementList(inline = true, name="item")
        public List<News> newsList;

        public List<News> getNewsList() {
            return newsList;
        }
    }
}