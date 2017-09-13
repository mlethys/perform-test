package mkabala.pl.perform_test.event;

import mkabala.pl.perform_test.model.Scores;

/**
 * Created by mkabala on 12.09.2017.
 */

public class ScoresDownloadedEvent {
    private Scores scores;

    public ScoresDownloadedEvent(Scores scores) {
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }
}
