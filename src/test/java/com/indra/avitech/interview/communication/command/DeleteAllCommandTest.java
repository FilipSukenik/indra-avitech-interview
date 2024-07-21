package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteAllCommandTest {

  @Test
  void nullUserDaoTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new DeleteAllCommand(null));
  }

  @Test
  void getterTest() {

    var userDaoMock = Mockito.mock(UserDao.class);
    var command = new DeleteAllCommand(userDaoMock);

    Assertions.assertEquals(userDaoMock, command.userDao());
  }
}