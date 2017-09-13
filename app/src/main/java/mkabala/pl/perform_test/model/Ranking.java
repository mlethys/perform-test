package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by mkabala on 13.09.2017.
 */

@Root(name = "ranking", strict = false)
public class Ranking {

    @Attribute(name = "rank")
    private int rank;

    @Attribute(name = "club_name")
    private String teamName;

    @Attribute(name = "matches_total")
    private int matchesTotal;

    @Attribute(name = "matches_won")
    private int matchesWon;

    @Attribute(name = "matches_draw")
    private int matchesDraw;

    @Attribute(name = "matches_lost")
    private int matchesLost;

    @Attribute(name = "goals_pro")
    private int goalsPro;

    @Attribute(name = "goals_against")
    private int goalsAgainst;

    @Attribute(name = "points")
    private int points;

    public int getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMatchesTotal() {
        return matchesTotal;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesDraw() {
        return matchesDraw;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public int getGoalsPro() {
        return goalsPro;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "rank=" + rank +
                ", teamName='" + teamName + '\'' +
                ", matchesTotal=" + matchesTotal +
                ", matchesWon=" + matchesWon +
                ", matchesDraw=" + matchesDraw +
                ", matchesLost=" + matchesLost +
                ", goalsPro=" + goalsPro +
                ", goalsAgainst=" + goalsAgainst +
                ", points=" + points +
                '}';
    }
}
