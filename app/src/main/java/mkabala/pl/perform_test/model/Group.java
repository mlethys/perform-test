package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by mkabala on 12.09.2017.
 */

@Root(name = "group", strict = false)
public class Group {

    @ElementList(name = "match", inline = true)
    List<Match> matchList;

    public List<Match> getMatchList() {
        return matchList;
    }

    @Root(name = "match", strict = false)
    public static class Match {

        @Attribute(name = "team_A_name")
        private String teamAName;

        @Attribute(name = "team_B_name")
        private String teamBName;

        @Attribute(name = "fs_A")
        private int teamAScore;

        @Attribute(name = "fs_B")
        private int teamBScore;

        public String getTeamAName() {
            return teamAName;
        }

        public String getTeamBName() {
            return teamBName;
        }

        public int getTeamAScore() {
            return teamAScore;
        }

        public int getTeamBScore() {
            return teamBScore;
        }
    }
}
