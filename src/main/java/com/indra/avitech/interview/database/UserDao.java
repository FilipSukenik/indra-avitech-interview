package com.indra.avitech.interview.database;

import com.indra.avitech.interview.database.model.User;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;

public class UserDao {

  private final HikariDataSource databaseConnection;

  public UserDao(HikariDataSource databaseConnection) {

    this.databaseConnection = databaseConnection;
  }

  public void add(User user) {

  }

  public List<User> getAll() {

    return null;
  }

  public void deleteAll() {

  }
}
