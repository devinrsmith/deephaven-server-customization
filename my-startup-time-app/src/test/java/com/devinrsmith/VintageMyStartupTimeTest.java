package com.devinrsmith;

import static org.assertj.core.api.Assertions.assertThat;

import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.ChunkType;
import io.deephaven.chunk.ObjectChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.chunk.util.hashing.ChunkEquals;
import io.deephaven.engine.table.ChunkSource.GetContext;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.Table;
import io.deephaven.engine.testutil.junit4.EngineCleanup;
import java.time.Instant;
import org.junit.Rule;
import org.junit.Test;

/** See {@link MyStartupTimeTest} for a JUnit 5 equivalent. */
public class VintageMyStartupTimeTest {
  @Rule public final EngineCleanup engineCleanup = new EngineCleanup();

  @Test
  public void nowTable() {
    final Table nowTable = MyStartupTimeApplication.nowTable(Instant.EPOCH);
    final ColumnSource<Instant> timestampCol = nowTable.getColumnSource("Timestamp", Instant.class);
    try (final GetContext getContext = timestampCol.makeGetContext(128)) {
      final Chunk<? extends Values> fooChunk =
          timestampCol.getChunk(getContext, nowTable.getRowSet());
      assertThat(
              ChunkEquals.makeEqual(ChunkType.Object)
                  .equalReduce(fooChunk, ObjectChunk.chunkWrap(new Instant[] {Instant.EPOCH})))
          .isTrue();
    }
  }
}
