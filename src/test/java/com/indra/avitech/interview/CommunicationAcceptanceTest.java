package com.indra.avitech.interview;

import com.indra.avitech.interview.communication.Consumer;
import com.indra.avitech.interview.communication.Producer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CommunicationAcceptanceTest {

  private BlockingQueue<String> messageBus;

  private Consumer consumer;

  private Producer producer;

  @BeforeEach
  void setUp() {

    messageBus = new LinkedBlockingQueue<>();
    consumer = Mockito.spy(new Consumer(messageBus));
    producer = Mockito.spy(new Producer(messageBus));
  }

  @Test
  void acceptanceCommunicationCriteria() throws InterruptedException {
    // given producer and consumer with shared message bus

    // when 5 commands are sent through producer
    producer.send("Add");
    producer.send("Add");
    producer.send("PrintAll");
    producer.send("DeleteAll");
    producer.send("PrintAll");

    // then consumer process is called 5 times
    Mockito.verify(consumer, Mockito.times(5)).process(any());
  }
}
