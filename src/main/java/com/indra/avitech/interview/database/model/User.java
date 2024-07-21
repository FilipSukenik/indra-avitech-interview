package com.indra.avitech.interview.database.model;

public record User(int id, String guid, String name) {

  public User {

    if (guid == null) {
      throw new IllegalArgumentException("guid cannot be null");
    }
  }
}
