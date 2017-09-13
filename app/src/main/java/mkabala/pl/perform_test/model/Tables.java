package mkabala.pl.perform_test.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by mkabala on 13.09.2017.
 */

@Root(name = "gsmrs", strict = false)
public class Tables {

    @Element(name = "method")
    private Method method;

    @Element(name = "competition")
    private Competition competition;

    public Method getMethod() {
        return method;
    }

    public Competition getCompetition() {
        return competition;
    }

    @Root(name = "competition", strict = false)
    public static class Competition {

        @Element(name = "season")
        private Season season;

        public Season getSeason() {
            return season;
        }

        @Root(name = "season", strict = false)
        public static class Season {

            @Element(name = "round")
            private Round round;

            public Round getRound() {
                return round;
            }

            @Root(name = "round", strict = false)
            public static class Round {

                @Element(name = "resultstable")
                private ResultTable resultTable;

                public ResultTable getResultTable() {
                    return resultTable;
                }

                @Root(name = "resultstable", strict = false)
                public static class ResultTable {

                    @ElementList(inline = true, name="ranking")
                    private List<Ranking> rankingList;

                    public List<Ranking> getRankingList() {
                        return rankingList;
                    }
                }
            }
        }
    }
}
