package com.indra.avitech.interview.printing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrintServiceTest {

  @Test
  void printNullUsersTest() {

    var service = new PrintService();
    Assertions.assertThrows(IllegalArgumentException.class, () -> service.printAll(null));
  }
}