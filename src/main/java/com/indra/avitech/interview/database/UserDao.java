package com.indra.avitech.interview.database;

import com.indra.avitech.interview.database.model.User;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

  private static final String INSERT_USER_TO_DATABASE_QUERY = "INSERT INTO susers (user_id, user_guid, user_name) VALUES "
    + "(?, ?, ?)";

  private static final String GET_ALL_USERS_QUERY = "SELECT user_id, user_guid, user_name FROM susers";

  private static final String DELETE_ALL_USERS_QUERY = "DELETE FROM susers WHERE 1";

  private final HikariDataSource databaseConnection;

  public UserDao(HikariDataSource databaseConnection) {

    if (databaseConnection == null) {
      throw new IllegalArgumentException("database connection cannot be null");
    }
    this.databaseConnection = databaseConnection;
  }

  public void add(User user) throws SQLException {

    if (user == null) {
      throw new IllegalArgumentException("cannot persist null user");
    }

    try (var connection = databaseConnection.getConnection();
         var statement = connection.prepareStatement(INSERT_USER_TO_DATABASE_QUERY)) {
      statement.setInt(1, user.id());
      statement.setString(2, user.guid());
      statement.setString(3, user.name());
      statement.execute();
    }
  }

  public List<User> getAll() throws SQLException {

    List<User> result = new ArrayList<>();
    try (var connection = databaseConnection.getConnection();
         var statement = connection.prepareStatement(GET_ALL_USERS_QUERY);
         var resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        result.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
      }
    }
    return result;
  }

  public void deleteAll() throws SQLException {

    try (var connection = databaseConnection.getConnection();
         var statement = connection.prepareStatement(DELETE_ALL_USERS_QUERY)) {
      statement.execute();
    }
  }
}
