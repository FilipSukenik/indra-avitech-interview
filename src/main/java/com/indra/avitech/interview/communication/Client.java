package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.util.concurrent.BlockingQueue;

public abstract class Client {

  protected final BlockingQueue<Command> messageBus;

  Client(BlockingQueue<Command> messageBus) {

    this.messageBus = messageBus;
  }
}
