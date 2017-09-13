package mkabala.pl.perform_test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import mkabala.pl.perform_test.MainActivity;
import mkabala.pl.perform_test.R;
import mkabala.pl.perform_test.adapter.ScoresListAdapter;
import mkabala.pl.perform_test.event.ScoresDownloadedEvent;
import mkabala.pl.perform_test.model.Group;
import mkabala.pl.perform_test.model.Method;

/**
 * Created by mkabala on 12.09.2017.
 */

public class ScoresFragment extends Fragment {

    private final int DELAY = 100;
    private final int PERIOD = 30000;

    private List<Group.Match> matchList;
    private List<Method.Parameter> parameterList;
    private View progressBar;
    private View scoresDateContainer;
    private TextView dateView;

    private RecyclerView scoresList;
    private Timer refresher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
        matchList = new ArrayList<>();
        progressBar = rootView.findViewById(R.id.scores_progress_bar);
        scoresDateContainer = rootView.findViewById(R.id.scores_date_container);
        dateView = (TextView) rootView.findViewById(R.id.date_scores_text);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        scoresList = (RecyclerView) rootView.findViewById(R.id.scores_list);
        scoresList.setLayoutManager(layoutManager);
        EventBus.getDefault().register(this);
        refresher = new Timer();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresher.schedule(new TimerTask() {
            @Override
            public void run() {
                if(getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity)getActivity()).callForScores();
                        }
                    });
                }
            }
        }, DELAY, PERIOD);
    }

    @Override
    public void onStop() {
        super.onStop();
        refresher.cancel();
    }

    private void refreshList() {
        ScoresListAdapter adapter = new ScoresListAdapter(matchList, getActivity());
        scoresList.setAdapter(adapter);
        dateView.setText(getDate());
        progressBar.setVisibility(View.GONE);
        scoresDateContainer.setVisibility(View.VISIBLE);
        scoresList.setVisibility(View.VISIBLE);
    }

    private String getDate() {
        for(Method.Parameter parameter : parameterList) {
            if(parameter.getName().equals("date")) {
                return parameter.getValue();
            }
        }
        return "";
    }


    public void onEventMainThread(ScoresDownloadedEvent ev) {
        matchList.clear();
        for(Group group : ev.getScores().getCompetition().getSeason().getRound().getGroupList()) {
            for(Group.Match match : group.getMatchList()) {
                matchList.add(match);
            }
        }
        parameterList = ev.getScores().getMethod().getParameterList();
        refreshList();
    }

}
