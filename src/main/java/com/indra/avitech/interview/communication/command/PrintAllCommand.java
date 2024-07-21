package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.printing.PrintService;
import java.sql.SQLException;

public record PrintAllCommand(PrintService printService, UserDao userDao) implements Command {

  public PrintAllCommand {

    if (userDao == null) {
      throw new IllegalArgumentException("userDao cannot be null");
    }
    if (printService == null) {
      throw new IllegalArgumentException("printService cannot be null");
    }
  }

  @Override
  public void execute() throws SQLException {

    printService.printAll(userDao.getAll());
  }
}
