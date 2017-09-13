package mkabala.pl.perform_test.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mkabala.pl.perform_test.R;
import mkabala.pl.perform_test.model.Ranking;

/**
 * Created by mkabala on 13.09.2017.
 */

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {

    private List<Ranking> rankingList;
    private Context context;

    public StandingsAdapter(List<Ranking> rankingList, Context context) {
        this.context = context;
        this.rankingList = rankingList;
    }

    @Override
    public StandingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_standings, parent, false);
        return new StandingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StandingsViewHolder holder, int position) {

        if(position == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            holder.rankText.setText(context.getString(R.string.club));
            holder.teamNameText.setText("");
            holder.matchesTotal.setText(context.getString(R.string.plays));
            holder.matchesWon.setText(context.getString(R.string.matches_won));
            holder.matchesDraw.setText(context.getString(R.string.matches_draw));
            holder.matchesLost.setText(context.getString(R.string.matches_lost));
            holder.goals.setText(context.getString(R.string.goals));
            holder.points.setText(context.getString(R.string.points));

        } else {
            final Ranking ranking = rankingList.get(position - 1);
            holder.rankText.setText(String.valueOf(ranking.getRank()));
            holder.teamNameText.setText(ranking.getTeamName());
            holder.matchesTotal.setText(String.valueOf(ranking.getMatchesTotal()));
            holder.matchesTotal.setTypeface(null, Typeface.BOLD);
            holder.matchesWon.setText(String.valueOf(ranking.getMatchesWon()));
            holder.matchesDraw.setText(String.valueOf(ranking.getMatchesDraw()));
            holder.matchesLost.setText(String.valueOf(ranking.getMatchesLost()));
            holder.goals.setText(String.valueOf(ranking.getGoalsPro() - ranking.getGoalsAgainst()));
            holder.points.setText(String.valueOf(ranking.getPoints()));
            holder.points.setTypeface(null, Typeface.BOLD);

            if(position % 2 == 0) {
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            }
        }

    }

    @Override
    public int getItemCount() {
        return rankingList.size() + 1;
    }

    class StandingsViewHolder extends RecyclerView.ViewHolder {

        private TextView rankText;
        private TextView teamNameText;
        private TextView matchesTotal;
        private TextView matchesWon;
        private TextView matchesDraw;
        private TextView matchesLost;
        private TextView goals;
        private TextView points;

        public StandingsViewHolder(View itemView) {
            super(itemView);

            rankText = (TextView) itemView.findViewById(R.id.rank_text);
            teamNameText = (TextView) itemView.findViewById(R.id.team_name_text);

            matchesTotal = (TextView) itemView.findViewById(R.id.metches_total);
            matchesWon = (TextView) itemView.findViewById(R.id.metches_won);
            matchesDraw = (TextView) itemView.findViewById(R.id.metches_draw);
            matchesLost = (TextView) itemView.findViewById(R.id.metches_lost);
            goals = (TextView) itemView.findViewById(R.id.goals);
            points = (TextView) itemView.findViewById(R.id.points);
        }
    }
}
