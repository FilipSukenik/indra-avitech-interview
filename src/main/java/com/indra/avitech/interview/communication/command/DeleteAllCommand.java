package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import java.sql.SQLException;

public record DeleteAllCommand(UserDao userDao) implements Command {

  @Override
  public void execute() throws SQLException {
    userDao.deleteAll();
  }
}
