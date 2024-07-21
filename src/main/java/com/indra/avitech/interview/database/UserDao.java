package com.indra.avitech.interview.database;

import com.indra.avitech.interview.database.model.User;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

  private static final String INSERT_USER_TO_DATABASE_QUERY = "INSERT INTO susers (user_id, user_guid, user_name) VALUES "
    + "(?, ?, ?)";

  private final HikariDataSource databaseConnection;

  public UserDao(HikariDataSource databaseConnection) {

    this.databaseConnection = databaseConnection;
  }

  public void add(User user) throws SQLException {

    try (var connection = databaseConnection.getConnection();
         var statement = connection.prepareStatement(INSERT_USER_TO_DATABASE_QUERY)) {
      statement.setInt(1, user.id());
      statement.setString(2, user.guid());
      statement.setString(3, user.name());
      statement.execute();
    }
  }

  public List<User> getAll() {

    return null;
  }

  public void deleteAll() {

  }
}
