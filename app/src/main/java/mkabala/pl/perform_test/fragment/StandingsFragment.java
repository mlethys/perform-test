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
import mkabala.pl.perform_test.adapter.StandingsAdapter;
import mkabala.pl.perform_test.event.StandingsDownloadedEvent;
import mkabala.pl.perform_test.model.Ranking;

/**
 * Created by mkabala on 13.09.2017.
 */

public class StandingsFragment extends Fragment {

    private List<Ranking> rankingList;
    private RecyclerView standingsList;
    private View progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_standings, container, false);
        EventBus.getDefault().register(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        standingsList = (RecyclerView) rootView.findViewById(R.id.standings_list);
        standingsList.setLayoutManager(layoutManager);
        progressBar = rootView.findViewById(R.id.standings_progressbar);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).callForStandings();
    }

    private void refreshList() {
        StandingsAdapter adapter = new StandingsAdapter(rankingList, getActivity());
        standingsList.setAdapter(adapter);
        standingsList.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(StandingsDownloadedEvent ev) {
        rankingList = ev.getTables().getCompetition().getSeason().getRound().getResultTable().getRankingList();
        refreshList();
    }
}
