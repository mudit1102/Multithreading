package com.java.multithreading;

public class TestBlockingQueue {

  public static void main(String[] args) {

    BlockingQueue queue = new BlockingQueue(10);
    Thread consumer1 = new Thread(new Consumer(queue));
    Thread producer = new Thread(new Producer(queue));

    Thread consumer2 = new Thread(new Consumer(queue));
    Thread consumer3 = new Thread(new Consumer(queue));

    producer.start();
    consumer1.start();
    consumer2.start();
    consumer3.start();
  }

}
