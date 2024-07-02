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
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DeephavenEngineExtension.class)
public class MyStartupTimeTest {

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
