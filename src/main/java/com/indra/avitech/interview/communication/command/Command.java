package com.indra.avitech.interview.communication.command;

import java.sql.SQLException;

public interface Command {

  void execute() throws SQLException;
}
