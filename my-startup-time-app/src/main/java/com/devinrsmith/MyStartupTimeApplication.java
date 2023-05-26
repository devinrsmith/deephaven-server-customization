package com.devinrsmith;

import io.deephaven.appmode.ApplicationState;
import io.deephaven.appmode.ApplicationState.Listener;
import io.deephaven.engine.table.Table;
import io.deephaven.engine.util.TableTools;
import io.deephaven.time.DateTime;

import javax.inject.Inject;

public class MyStartupTimeApplication implements ApplicationState.Factory {

    @Inject
    public MyStartupTimeApplication() {
    }

    @Override
    public ApplicationState create(Listener appStateListener) {
        final ApplicationState state = new ApplicationState(appStateListener, "com.devinrsmith", "MyStartupTime");
        final DateTime now = DateTime.now();
        final Table startupTimestamp = TableTools.newTable(TableTools.dateTimeCol("Timestamp", now));
        state.setField("startup_timestamp", startupTimestamp);;
        return state;
    }
}
