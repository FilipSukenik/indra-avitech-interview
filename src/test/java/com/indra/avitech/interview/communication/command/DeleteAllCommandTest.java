package com.indra.avitech.interview.communication.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 class DeleteAllCommandTest {
  @Test
  void nullUserDaoTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new DeleteAllCommand(null));
  }
}