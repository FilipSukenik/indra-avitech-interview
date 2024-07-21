package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Client implements Runnable {

  private boolean isRunning;

  public Consumer(BlockingQueue<Command> messageBus) {

    super(messageBus);
  }

  public Command consume() throws InterruptedException {

    return messageBus.take();
  }

  public void process(Command message) {

  }

  @Override
  public void run() {

    while (isRunning && !Thread.currentThread().isInterrupted()) {
      try {
        Command message = consume();
        process(message);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void listenAsync(Thread backgroundProcess) {

    isRunning = true;
    backgroundProcess.start();
  }

  void setRunning(boolean running) {

    isRunning = running;
  }
}
