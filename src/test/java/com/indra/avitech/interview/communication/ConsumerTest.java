package com.indra.avitech.interview.communication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConsumerTest {

  @Test
  void nullMessageBusTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new Consumer(null));
  }
}
