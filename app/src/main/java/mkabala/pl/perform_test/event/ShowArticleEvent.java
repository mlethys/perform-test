package mkabala.pl.perform_test.event;

import android.util.Log;

/**
 * Created by mkabala on 12.09.2017.
 */

public class ShowArticleEvent {

    private String url;

    public ShowArticleEvent(String url) {
        this.url = url;
        Log.i("webview", "url: " + url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
