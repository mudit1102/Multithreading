package com.java.multithreading;

import com.java.multithreading.impl.ConsumerImpl;
import com.java.multithreading.impl.ProducerImpl;

public class TestBlockingQueue {

  public static void main(String[] args) {

    BlockingQueue queue = new BlockingQueue<Integer>(10);

    Thread producer1 = new Thread(new ProducerImpl(queue));
    Thread producer2 = new Thread(new ProducerImpl(queue));
    Thread consumer1 = new Thread(new ConsumerImpl(queue));
    Thread consumer2 = new Thread(new ConsumerImpl(queue));

    producer1.start();
    producer2.start();

    consumer1.start();
    consumer2.start();

    try {
      producer1.join();
      producer2.join();

      consumer1.join();
      consumer2.join();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
