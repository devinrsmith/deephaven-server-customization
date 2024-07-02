package com.devinrsmith;

import static org.assertj.core.api.Assertions.assertThat;

import io.deephaven.engine.table.Table;
import org.junit.jupiter.api.Test;

class BasicTest {

  @Test
  void tablePackage() {
    assertThat(Table.class).hasPackage("io.deephaven.engine.table");
  }
}
