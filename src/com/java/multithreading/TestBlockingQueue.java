package com.java.multithreading;

import com.java.multithreading.impl.ConsumerImpl;
import com.java.multithreading.impl.ProducerImpl;

public class TestBlockingQueue {

  public static void main(String[] args) {

    BlockingQueue queue = new BlockingQueue<Integer>(10);
    Thread consumer1 = new Thread(new ConsumerImpl(queue));
    Thread producer = new Thread(new ProducerImpl(queue));

    Thread consumer2 = new Thread(new ConsumerImpl(queue));
    Thread consumer3 = new Thread(new ConsumerImpl(queue));

    producer.start();
    consumer1.start();
    consumer2.start();
    consumer3.start();

    try {
      producer.join();
      consumer1.join();
      consumer2.join();
      consumer3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
