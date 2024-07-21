package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.printing.PrintService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PrintAllCommandTest {

  @Test
  void nullUserDaoTest() {

    var userDaoMock = Mockito.mock(UserDao.class);
    Assertions.assertThrows(IllegalArgumentException.class, () -> new PrintAllCommand(null, userDaoMock));
  }

  @Test
  void nullPrintServiceTest() {

    var printServiceMock = Mockito.mock(PrintService.class);
    Assertions.assertThrows(IllegalArgumentException.class, () -> new PrintAllCommand(printServiceMock, null));
  }

}