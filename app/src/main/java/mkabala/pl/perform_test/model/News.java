package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by mkabala on 12.09.2017.
 */

@Root(name = "item")
public class News {

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;

    @Element(name = "enclosure")
    private Enclosure enclosure;

    @Element(name = "guid")
    private String guid;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "category")
    private String category;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getCategory() {
        return category;
    }

    public static class Enclosure {

        @Attribute(name = "url")
        private String url;

        @Attribute(name = "length")
        private long length;

        @Attribute(name = "type")
        private String type;

        public String getUrl() {
            return url;
        }

        public long getLength() {
            return length;
        }

        public String getType() {
            return type;
        }
    }
}
