package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.database.model.User;
import java.sql.SQLException;

public record AddCommand(User user, UserDao userDao) implements Command {

  public AddCommand {

    if (user == null) {
      throw new IllegalArgumentException("user cannot be null");
    }
    if (userDao == null) {
      throw new IllegalArgumentException("userDao cannot be null");
    }
  }

  @Override
  public void execute() throws SQLException {

    userDao.add(user);
  }
}
