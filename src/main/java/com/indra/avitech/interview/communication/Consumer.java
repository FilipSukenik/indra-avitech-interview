package com.indra.avitech.interview.communication;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Client {

  public Consumer(BlockingQueue<String> messageBus) {

    super(messageBus);
  }

  public void consume() throws InterruptedException {

    String message = messageBus.take();
    process(message);
  }

  public void process(String message) {

  }
}
