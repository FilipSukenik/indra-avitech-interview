package com.indra.avitech.interview.communication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;

public class ConsumerTest {

  private Consumer consumer;

  @BeforeEach
  void setUp() {

    BlockingQueue<String> messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
  }

  @Test
  void consumeThrowsInterruptedExceptionExpectThreadToBeInterrupted() throws InterruptedException {

    doThrow(new InterruptedException()).when(consumer).consume();
    consumer.setRunning(true);
    consumer.run();

    Assertions.assertTrue(Thread.currentThread().isInterrupted());
  }
}