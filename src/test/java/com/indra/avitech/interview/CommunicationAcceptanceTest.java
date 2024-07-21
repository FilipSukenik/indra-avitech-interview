package com.indra.avitech.interview;

import com.indra.avitech.interview.communication.Consumer;
import com.indra.avitech.interview.communication.Producer;
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

  @BeforeEach
  void setUp() {

    BlockingQueue<String> messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
    producer = Mockito.spy(new Producer(messageBus));
  }

  @Test
  void acceptanceCommunicationCriteria() throws InterruptedException {
    // given producer and consumer with shared message bus and consumer running in own thread
    Thread backgroundProcess = new Thread(consumer);
    consumer.listenAsync(backgroundProcess);

    // when 5 commands are sent through producer
    producer.send("Add");
    producer.send("Add");
    producer.send("PrintAll");
    producer.send("DeleteAll");
    producer.send("PrintAll");

    // then consumer process is called 5 times asynchronously
    await().atMost(Duration.ofSeconds(5)).untilAsserted(() ->
      Mockito.verify(consumer, Mockito.times(5)).process(any())
    );
  }
}
