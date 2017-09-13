package mkabala.pl.perform_test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import mkabala.pl.perform_test.R;

/**
 * Created by mkabala on 12.09.2017.
 */

public class WebviewFragment extends Fragment {

    public static final String URL_KEY = "page_url";

    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            url = bundle.getString(URL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WebView rootView = (WebView) inflater.inflate(R.layout.fragment_webview, container, false);
        rootView.setInitialScale(1);
        rootView.getSettings().setLoadWithOverviewMode(true);
        rootView.getSettings().setUseWideViewPort(true);
        if(url != null) {
            rootView.loadUrl(url);
        }
        return rootView;
    }
}
