package mkabala.pl.perform_test.event;

import mkabala.pl.perform_test.model.NewsList;

/**
 * Created by mkabala on 12.09.2017.
 */

public class NewsFeedDownloadedEvent {

    private NewsList feed;

    public NewsFeedDownloadedEvent(NewsList feed) {
        this.feed = feed;
    }

    public NewsList getFeed() {
        return feed;
    }

    public void setFeed(NewsList feed) {
        this.feed = feed;
    }
}
