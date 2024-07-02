package com.devinrsmith;

import io.deephaven.appmode.ApplicationState;
import io.deephaven.appmode.ApplicationState.Listener;
import io.deephaven.engine.table.Table;
import io.deephaven.engine.util.TableTools;
import java.time.Instant;
import javax.inject.Inject;

public class MyStartupTimeApplication implements ApplicationState.Factory {

  @Inject
  public MyStartupTimeApplication() {}

  @Override
  public ApplicationState create(Listener appStateListener) {
    final ApplicationState state =
        new ApplicationState(appStateListener, "com.devinrsmith", "MyStartupTime");
    final Instant now = Instant.now();
    final Table startupTimestamp = nowTable(now);
    state.setField("startup_timestamp", startupTimestamp);
    return state;
  }

  static Table nowTable(Instant now) {
    return TableTools.newTable(TableTools.instantCol("Timestamp", now));
  }
}
