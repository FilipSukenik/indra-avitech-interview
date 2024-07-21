package com.indra.avitech.interview.communication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProducerTest {

  @Test
  public void nullMessageBusTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new Producer(null));
  }
}