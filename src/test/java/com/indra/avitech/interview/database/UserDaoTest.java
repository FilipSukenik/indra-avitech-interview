package com.indra.avitech.interview.database;

import com.indra.avitech.interview.DatabaseConfig;
import com.indra.avitech.interview.database.model.User;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserDaoTest {

  private static final String COUNT_USERS_QUERY = "SELECT COUNT(user_id) FROM susers";

  private DatabaseConfig databaseConfig;

  private UserDao userDao;

  @BeforeEach
  void setUp() throws SQLException, IOException {

    databaseConfig = new DatabaseConfig();
    databaseConfig.setUp();
    userDao = Mockito.spy(new UserDao(databaseConfig.getDatabase()));
  }

  @AfterEach
  void tearDown() {

    databaseConfig = new DatabaseConfig();
    databaseConfig.teardown();
    userDao = Mockito.spy(new UserDao(databaseConfig.getDatabase()));
  }

  @Test
  void callAddUserExpectUserToBeInDatabase() throws SQLException {

    userDao.add(new User(1, "test-uuid", "test-name"));
    try (var connection = databaseConfig.getDatabase().getConnection();
         var statement = connection.prepareStatement(COUNT_USERS_QUERY);
         var resultSet = statement.executeQuery()
    ) {
      Assertions.assertTrue(resultSet.next());
      int count = resultSet.getInt(1);
      Assertions.assertEquals(1, count);
    }
  }
}