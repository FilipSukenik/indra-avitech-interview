package com.indra.avitech.interview.communication;

import java.util.concurrent.BlockingQueue;

public abstract class Client {

  protected final BlockingQueue<String> messageBus;

  Client(BlockingQueue<String> messageBus) {

    this.messageBus = messageBus;
  }
}
