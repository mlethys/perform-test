package mkabala.pl.perform_test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import mkabala.pl.perform_test.MainActivity;
import mkabala.pl.perform_test.R;
import mkabala.pl.perform_test.adapter.NewsListAdapter;
import mkabala.pl.perform_test.event.NewsFeedDownloadedEvent;
import mkabala.pl.perform_test.model.News;

/**
 * Created by mkabala on 12.09.2017.
 */

public class NewsFragment extends Fragment {

    private List<News> rssFeed;

    private RecyclerView newsList;
    private View progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsList = (RecyclerView) rootView.findViewById(R.id.news_list);
        newsList.setLayoutManager(layoutManager);
        progressBar = rootView.findViewById(R.id.news_list_progress_bar);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).callForNews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void refreshList() {
        NewsListAdapter adapter = new NewsListAdapter(rssFeed);
        newsList.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        newsList.setVisibility(View.VISIBLE);
    }

    public void onEventMainThread(NewsFeedDownloadedEvent ev) {
        rssFeed = ev.getFeed().getChannel().getNewsList();
        refreshList();
    }
}
