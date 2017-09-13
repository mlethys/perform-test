package mkabala.pl.perform_test.event;

import mkabala.pl.perform_test.model.Tables;

/**
 * Created by mkabala on 13.09.2017.
 */

public class StandingsDownloadedEvent {

    private Tables tables;

    public StandingsDownloadedEvent(Tables tables) {
        this.tables = tables;
    }

    public Tables getTables() {
        return tables;
    }
}
