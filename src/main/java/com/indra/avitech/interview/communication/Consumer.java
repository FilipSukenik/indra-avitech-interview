package com.indra.avitech.interview.communication;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Client implements Runnable {

  private boolean isRunning;

  public Consumer(BlockingQueue<String> messageBus) {

    super(messageBus);
  }

  public String consume() throws InterruptedException {

    return messageBus.take();
  }

  public void process(String message) {

  }

  @Override
  public void run() {

    while (isRunning && !Thread.currentThread().isInterrupted()) {
      try {
        String message = consume();
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
