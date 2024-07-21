package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.database.model.User;
import java.sql.SQLException;

public record AddCommand(User user, UserDao userDao) implements Command {

  @Override
  public void execute() throws SQLException {
    userDao.add(user);
  }
}
