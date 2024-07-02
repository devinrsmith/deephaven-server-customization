package com.devinrsmith;

import static org.assertj.core.api.Assertions.assertThat;

import io.deephaven.engine.table.Table;
import org.junit.Test;

/** See {@link BasicTest} for a JUnit 5 version. */
public class VintageBasicTest {

  @Test
  public void tablePackage() {
    assertThat(Table.class).hasPackage("io.deephaven.engine.table");
  }
}
