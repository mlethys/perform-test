package mkabala.pl.perform_test;

import mkabala.pl.perform_test.model.NewsList;
import mkabala.pl.perform_test.model.Scores;
import mkabala.pl.perform_test.model.Tables;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mkabala on 12.09.2017.
 */

public interface ApiInterfaces {

    @GET("latestnews.xml")
    Call<NewsList> getNewsList();

    @GET("scores.xml")
    Call<Scores> getScores();

    @GET("standings.xml")
    Call<Tables> getStandings();
}
