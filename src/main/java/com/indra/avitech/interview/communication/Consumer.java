package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Client implements Runnable {

  public Consumer(BlockingQueue<Command> messageBus) {

    super(messageBus);
  }

  public Command consume() throws InterruptedException {

    return messageBus.take();
  }

  public void process(Command command) throws SQLException {

    command.execute();
  }

  @Override
  public void run() {

    while (!Thread.currentThread().isInterrupted()) {
      try {
        Command message = consume();
        process(message);
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
