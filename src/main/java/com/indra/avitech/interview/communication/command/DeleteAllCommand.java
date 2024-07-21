package com.indra.avitech.interview.communication.command;

import com.indra.avitech.interview.database.UserDao;

public record DeleteAllCommand(UserDao userDao) implements Command {

  @Override
  public void execute() {

  }
}
