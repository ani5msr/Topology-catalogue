package dev.catalogue.topology.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class MainTest {

  private Main cut;

  @BeforeEach
  void setUp() {
    this.cut = new Main();
  }

  @Test
  void firstTest() {
    String input = "duke";

    String result = cut.format(input);

    assertThat(result).isEqualTo("DUKE");
  }
}
