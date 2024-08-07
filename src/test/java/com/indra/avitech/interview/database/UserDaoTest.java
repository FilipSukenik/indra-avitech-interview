package com.indra.avitech.interview.database;

import com.indra.avitech.interview.DatabaseConfig;
import com.indra.avitech.interview.database.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserDaoTest {

  private static final String COUNT_USERS_QUERY = "SELECT COUNT(user_id) FROM susers";

  private static final String INSERT_3_USER__QUERY = "INSERT INTO susers (user_id, user_guid, user_name) VALUES "
    + "(1, 'test-guid1', 'test-name1'), "
    + "(2, 'test-guid2', 'test-name2'), "
    + "(3, 'test-guid3', 'test-name3')";

  private DatabaseConfig databaseConfig;

  private UserDao userDao;

  @BeforeEach
  void setUp() throws SQLException, IOException {

    databaseConfig = new DatabaseConfig();
    databaseConfig.setUp();
    userDao = Mockito.spy(new UserDao(databaseConfig.getDatabase()));
  }

  @AfterEach
  void tearDown() throws IOException {

    databaseConfig.teardown();
  }

  @Test
  void nullConnectionTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new UserDao(null));
  }

  @Test
  void callAddUserExpectUserToBeInDatabase() throws SQLException {

    userDao.add(new User(1, "test-uuid", "test-name"));
    assertUserCount(1);
  }

  @Test
  void callAddUniqueUserMultipleTimesExpectAllUsersInDb() throws SQLException {

    userDao.add(new User(1, "test-uuid1", "test-name1"));
    userDao.add(new User(2, "test-uuid2", "test-name2"));
    userDao.add(new User(3, "test-uuid3", "test-name3"));
    assertUserCount(3);
  }

  @Test
  void addTwoUsersWithSameIdExpectConflict() throws SQLException {

    userDao.add(new User(1, "test-uuid", "test-name"));
    Assertions.assertThrows(JdbcSQLIntegrityConstraintViolationException.class,
      () -> userDao.add(new User(1, "test-uuid2", "test-name")));
  }

  @Test
  void addTwoUsersWithSameGuidExpectConflict() throws SQLException {

    userDao.add(new User(1, "test-uuid", "test-name"));
    Assertions.assertThrows(JdbcSQLIntegrityConstraintViolationException.class,
      () -> userDao.add(new User(2, "test-uuid", "test-name")));
  }

  @Test
  void insert3UsersToDBCallGetAllAndExpect3UsersReturned() throws SQLException {

    insertTestUsers();
    List<User> users = userDao.getAll();
    Assertions.assertEquals(3, users.size());
  }

  @Test
  void callGetAllOnEmptyDatabaseExpect0UsersInDb() throws SQLException {

    List<User> users = userDao.getAll();
    Assertions.assertTrue(users.isEmpty());
  }

  @Test
  void insert3UsersToDatabaseCallDeleteExpectTableToBeEmpty() throws SQLException {

    insertTestUsers();
    assertUserCount(3);
    userDao.deleteAll();
    assertUserCount(0);
  }

  @Test
  void deleteAllOnEmptyTableDoesNotThrowException() throws SQLException {

    Assertions.assertDoesNotThrow(() -> userDao.deleteAll());
    assertUserCount(0);
  }

  @Test
  void storeNullUserTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> userDao.add(null));
  }

  private void insertTestUsers() throws SQLException {

    try (var connection = databaseConfig.getDatabase().getConnection();
         var statement = connection.prepareStatement(INSERT_3_USER__QUERY)
    ) {
      statement.execute();
    }
  }

  private void assertUserCount(int expected) throws SQLException {

    try (var connection = databaseConfig.getDatabase().getConnection();
         var statement = connection.prepareStatement(COUNT_USERS_QUERY);
         var resultSet = statement.executeQuery()
    ) {
      Assertions.assertTrue(resultSet.next());
      int count = resultSet.getInt(1);
      Assertions.assertEquals(expected, count);
    }
  }
}
