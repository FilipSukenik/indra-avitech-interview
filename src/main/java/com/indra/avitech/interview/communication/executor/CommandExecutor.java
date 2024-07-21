package com.indra.avitech.interview.communication.executor;

import com.indra.avitech.interview.communication.Consumer;
import com.indra.avitech.interview.communication.command.Command;
import java.sql.SQLException;

public class CommandExecutor implements Runnable {

  private final Consumer consumer;

  public CommandExecutor(Consumer consumer) {

    this.consumer = consumer;
  }

  public void process(Command command) throws SQLException {

    command.execute();
  }

  @Override
  public void run() {

    while (!Thread.currentThread().isInterrupted()) {
      try {
        Command message = consumer.consume();
        if (message != null) {
          process(message);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (SQLException e) {
        throw new RuntimeException("SQLException thrown on message process", e);
      }
    }
  }

  public void listenAsync(Thread backgroundProcess) {

    backgroundProcess.start();
  }
}
