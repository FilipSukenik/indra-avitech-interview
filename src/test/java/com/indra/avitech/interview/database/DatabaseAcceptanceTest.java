package com.indra.avitech.interview.database;

import com.indra.avitech.interview.DatabaseConfig;
import com.indra.avitech.interview.database.model.User;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DatabaseAcceptanceTest {

  private UserDao userDao;

  private DatabaseConfig databaseConfig;

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
  void databaseAcceptanceCriteria() throws SQLException {
    // given user dao

    // when 2 adds and one deleteAll are called,
    userDao.add(new User(1, "a1", "Robert"));
    Assertions.assertEquals(1, userDao.getAll().size());

    userDao.add(new User(2, "a2", "Martin"));
    Assertions.assertEquals(2, userDao.getAll().size());

    userDao.deleteAll();

    // then getAll returns empty list
    assertFalse(userDao.getAll().isEmpty());
  }
}
