package com.indra.avitech.interview.database.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void nullGuidTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new User(1, null, "name"));
  }
}
