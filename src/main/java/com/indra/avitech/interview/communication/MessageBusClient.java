package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.util.concurrent.BlockingQueue;

public abstract class MessageBusClient {

  protected final BlockingQueue<Command> messageBus;

  MessageBusClient(BlockingQueue<Command> messageBus) {

    if (messageBus == null) {
      throw new IllegalArgumentException("message bus cannot be null for client to work properly");
    }
    this.messageBus = messageBus;
  }
}
