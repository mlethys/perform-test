package mkabala.pl.perform_test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import mkabala.pl.perform_test.event.NewsFeedDownloadedEvent;
import mkabala.pl.perform_test.event.ScoresDownloadedEvent;
import mkabala.pl.perform_test.event.ShowArticleEvent;
import mkabala.pl.perform_test.event.StandingsDownloadedEvent;
import mkabala.pl.perform_test.fragment.NewsFragment;
import mkabala.pl.perform_test.fragment.ScoresFragment;
import mkabala.pl.perform_test.fragment.StandingsFragment;
import mkabala.pl.perform_test.fragment.WebviewFragment;
import mkabala.pl.perform_test.model.NewsList;
import mkabala.pl.perform_test.model.Scores;
import mkabala.pl.perform_test.model.Tables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://www.mobilefeeds.performgroup.com/utilities/interviews/techtest/";
    private ApiInterfaces apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);
        if(savedInstanceState == null) {
            changeFragments(new NewsFragment(), R.id.fragment_container, true);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiInterfaces.class);
        callForNews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_news:
                changeFragments(new NewsFragment(), R.id.fragment_container, false);
                callForNews();
                break;
            case R.id.action_scores:
                changeFragments(new ScoresFragment(), R.id.fragment_container, false);
                callForScores();
                break;
            case R.id.action_standings:
                changeFragments(new StandingsFragment(), R.id.fragment_container, false);
                callForStandings();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeFragments(Fragment fragment, int containerId, boolean addToBackstack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(addToBackstack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.replace(containerId, fragment).commit();
    }

    public void callForNews() {

        Call<NewsList> call = apiService.getNewsList();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList>call, @NonNull Response<NewsList> response) {
                EventBus.getDefault().post(new NewsFeedDownloadedEvent(response.body()));

            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void callForScores() {
        Call<Scores> call = apiService.getScores();
        call.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(@NonNull Call<Scores>call, @NonNull Response<Scores> response) {
                EventBus.getDefault().post(new ScoresDownloadedEvent(response.body()));

            }

            @Override
            public void onFailure(@NonNull Call<Scores> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void callForStandings() {
        Call<Tables> call = apiService.getStandings();
        call.enqueue(new Callback<Tables>() {
            @Override
            public void onResponse(@NonNull Call<Tables> call, @NonNull Response<Tables> response) {
                Log.i("standings", response.body().toString());
                EventBus.getDefault().post(new StandingsDownloadedEvent(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<Tables> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void handleNewsClick(String url) {
        Fragment webViewFragment = new WebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebviewFragment.URL_KEY, url);
        webViewFragment.setArguments(bundle);
        changeFragments(webViewFragment, R.id.fragment_container, true);
    }

    public void onEventMainThread(ShowArticleEvent ev) {
        handleNewsClick(ev.getUrl());
    }
}
