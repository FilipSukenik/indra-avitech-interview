package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.database.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AddCommandTest {

  @Test
  void nullUserTest() {

    var userDaoMock = Mockito.mock(UserDao.class);
    Assertions.assertThrows(IllegalArgumentException.class, () -> new AddCommand(null, userDaoMock));
  }

  @Test
  void nullUserDaoTest() {

    var userMock = Mockito.mock(User.class);
    Assertions.assertThrows(IllegalArgumentException.class, () -> new AddCommand(userMock, null));
  }

  @Test
  void gettersCoverageTest() {

    var userDaoMock = Mockito.mock(UserDao.class);
    var userMock = Mockito.mock(User.class);

    var command = new AddCommand(userMock, userDaoMock);
    Assertions.assertEquals(userDaoMock, command.userDao());
    Assertions.assertEquals(userMock, command.user());
  }
}