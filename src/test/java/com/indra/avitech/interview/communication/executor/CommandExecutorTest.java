package com.indra.avitech.interview.communication.executor;

import com.indra.avitech.interview.communication.Consumer;
import com.indra.avitech.interview.communication.command.AddCommand;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class CommandExecutorTest {

  private Consumer consumer;

  private CommandExecutor executor;

  @BeforeEach
  void setUp() {

    consumer = Mockito.mock();
    executor = Mockito.spy(new CommandExecutor(consumer));
  }

  @Test
  void nullConsumerTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> new CommandExecutor(null));
  }

  @Test
  void processNullCommandTest() {

    Assertions.assertThrows(IllegalArgumentException.class, () -> executor.process(null));
  }

  @Test
  void consumeThrowsInterruptedExceptionExpectThreadToBeInterrupted() throws InterruptedException {

    doThrow(new InterruptedException()).when(consumer).consume();
    executor.run();

    Assertions.assertTrue(Thread.currentThread().isInterrupted());
  }

  @Test
  void processThrowsSQLExceptionExpectRuntimeExceptionBeingRethrown() throws SQLException, InterruptedException {

    doReturn(Mockito.mock(AddCommand.class)).when(consumer).consume();
    doThrow(new SQLException()).when(executor).process(any());

    Assertions.assertThrows(RuntimeException.class, () -> executor.run());
  }
}