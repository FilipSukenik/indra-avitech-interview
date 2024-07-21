package com.indra.avitech.interview.communication;

import com.indra.avitech.interview.communication.command.AddCommand;
import com.indra.avitech.interview.communication.command.Command;
import com.indra.avitech.interview.communication.command.DeleteAllCommand;
import com.indra.avitech.interview.communication.command.PrintAllCommand;
import com.indra.avitech.interview.communication.executor.CommandExecutor;
import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.database.model.User;
import com.indra.avitech.interview.printing.PrintService;
import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static org.awaitility.Awaitility.await;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;

class CommunicationAcceptanceTest {

  private Consumer consumer;

  private Producer producer;

  private UserDao userDao;

  private PrintService printService;

  private CommandExecutor executor;

  @BeforeEach
  void setUp() {

    BlockingQueue<Command> messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
    producer = Mockito.spy(new Producer(messageBus));
    userDao = Mockito.mock();
    printService = Mockito.mock();
    executor = Mockito.spy(new CommandExecutor(consumer));
  }

  @Test
  void acceptanceCommunicationCriteria() throws InterruptedException {
    // given producer and consumer with shared message bus and consumer running in own thread
    Thread backgroundProcess = new Thread(executor);
    executor.listenAsync(backgroundProcess);

    // when 5 commands are sent through producer
    producer.send(new AddCommand(new User(1, "a1", "Robert"), userDao));
    producer.send(new AddCommand(new User(2, "a2", "Martin"), userDao));
    producer.send(new PrintAllCommand(printService, userDao));
    producer.send(new DeleteAllCommand(userDao));
    producer.send(new PrintAllCommand(printService, userDao));

    // then consumer process is called 5 times asynchronously
    await().atMost(Duration.ofSeconds(5)).untilAsserted(() ->
      Mockito.verify(executor, Mockito.times(5)).process(any())
    );
  }
}
