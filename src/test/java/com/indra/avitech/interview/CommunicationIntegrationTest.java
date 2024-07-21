package com.indra.avitech.interview;

import com.indra.avitech.interview.communication.Consumer;
import com.indra.avitech.interview.communication.Producer;
import com.indra.avitech.interview.communication.command.AddCommand;
import com.indra.avitech.interview.communication.command.Command;
import com.indra.avitech.interview.communication.command.DeleteAllCommand;
import com.indra.avitech.interview.communication.command.PrintAllCommand;
import com.indra.avitech.interview.database.UserDao;
import com.indra.avitech.interview.database.model.User;
import com.indra.avitech.interview.printing.PrintService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;

class CommunicationIntegrationTest {

  private UserDao userDao;

  private DatabaseConfig databaseConfig;

  private Consumer consumer;

  private Producer producer;

  private PrintService printService;

  @BeforeEach
  void setUp() throws SQLException, IOException {

    databaseConfig = new DatabaseConfig();
    databaseConfig.setUp();
    userDao = Mockito.spy(new UserDao(databaseConfig.getDatabase()));

    BlockingQueue<Command> messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
    producer = Mockito.spy(new Producer(messageBus));

    printService = Mockito.spy(new PrintService());
  }

  @AfterEach
  void tearDown() throws IOException {

    databaseConfig.teardown();
  }

  @Test
  void executeWholeUseCase() throws InterruptedException {
    // given all variables and consumer running in own thread
    Thread backgroundProcess = new Thread(consumer);
    consumer.listenAsync(backgroundProcess);

    // when 5 specific commands are sent in specific order
    producer.send(new AddCommand(new User(1, "a1", "Robert"), userDao));
    producer.send(new AddCommand(new User(2, "a2", "Martin"), userDao));
    producer.send(new PrintAllCommand(printService, userDao));
    producer.send(new DeleteAllCommand(userDao));
    producer.send(new PrintAllCommand(printService, userDao));

    // then all components are called right amount of times and database is empty
    Awaitility.await().atMost(Duration.ofSeconds(5)).untilAsserted(() -> {
      Mockito.verify(userDao, Mockito.times(1)).deleteAll();
      Mockito.verify(userDao, Mockito.times(2)).add(any());
      Mockito.verify(printService, Mockito.times(2)).printAll(any());
      Assertions.assertTrue(userDao.getAll().isEmpty());
    });
  }
}
