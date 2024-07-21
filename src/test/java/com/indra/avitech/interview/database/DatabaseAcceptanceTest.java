package com.indra.avitech.interview.database;

import com.indra.avitech.interview.database.model.User;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DatabaseAcceptanceTest {

  private UserDao userDao;

  @BeforeEach
  void setUp() {

    userDao = Mockito.spy(new UserDao());
  }

  @Test
  void databaseAcceptanceCriteria() {
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
