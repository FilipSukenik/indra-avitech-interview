package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.Command;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Client {

  public Consumer(BlockingQueue<Command> messageBus) {

    super(messageBus);
  }

  public Command consume() throws InterruptedException {

    return messageBus.take();
  }
}
