package com.indra.avitech.interview.communication;

import java.util.concurrent.BlockingQueue;

public class Producer extends Client {

  public Producer(BlockingQueue<String> messageBus) {

    super(messageBus);
  }

  public void send(String message) throws InterruptedException {

    messageBus.put(message);
  }
}
