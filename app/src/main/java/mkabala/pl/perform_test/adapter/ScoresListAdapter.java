package mkabala.pl.perform_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mkabala.pl.perform_test.R;
import mkabala.pl.perform_test.model.Group;

/**
 * Created by mkabala on 12.09.2017.
 */

public class ScoresListAdapter extends RecyclerView.Adapter<ScoresListAdapter.ScoresViewHolder> {

    private List<Group.Match> matches;
    private Context context;

    public ScoresListAdapter(List<Group.Match> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @Override
    public ScoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scores, parent, false);
        return new ScoresViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoresViewHolder holder, int position) {
        final Group.Match match = matches.get(position);
        if(position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }
        holder.homeTeam.setText(match.getTeamAName());
        holder.awayTeam.setText(match.getTeamBName());
        holder.scores.setText(match.getTeamAScore() + " - " + match.getTeamBScore());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    class ScoresViewHolder extends RecyclerView.ViewHolder {

        private TextView homeTeam;

        private TextView awayTeam;

        private TextView scores;

        public ScoresViewHolder(View itemView) {
            super(itemView);
            homeTeam = (TextView) itemView.findViewById(R.id.home_team_textview);
            awayTeam = (TextView) itemView.findViewById(R.id.guest_team_textview);
            scores = (TextView) itemView.findViewById(R.id.scores_textview);
        }
    }
}
