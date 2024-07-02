package com.devinrsmith;

import io.deephaven.engine.table.Table;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BasicTest {

    @Test
    void tablePackage() {
        assertThat(Table.class).hasPackage("io.deephaven.engine.table");
    }
}
