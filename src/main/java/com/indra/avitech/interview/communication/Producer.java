package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.util.concurrent.BlockingQueue;

public class Producer extends Client {

  public Producer(BlockingQueue<Command> messageBus) {

    super(messageBus);
  }

  public void send(Command message) throws InterruptedException {

    messageBus.put(message);
  }
}
