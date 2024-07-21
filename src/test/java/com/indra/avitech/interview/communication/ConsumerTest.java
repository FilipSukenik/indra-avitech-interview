package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.AddCommand;
import com.indra.avitech.interview.communication.command.Command;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class ConsumerTest {

  private Consumer consumer;

  @BeforeEach
  void setUp() {

    BlockingQueue<Command> messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
  }

  @Test
  void consumeThrowsInterruptedExceptionExpectThreadToBeInterrupted() throws InterruptedException {

    doThrow(new InterruptedException()).when(consumer).consume();
    consumer.run();

    Assertions.assertTrue(Thread.currentThread().isInterrupted());
  }

  @Test
  void processThrowsSQLExceptionExpectRuntimeExceptionBeingRethrown() throws SQLException, InterruptedException {

    doReturn(Mockito.mock(AddCommand.class)).when(consumer).consume();
    doThrow(new SQLException()).when(consumer).process(any());

    Assertions.assertThrows(RuntimeException.class, () -> consumer.run());
  }
}