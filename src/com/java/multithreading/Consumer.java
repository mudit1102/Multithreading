package com.java.multithreading;

public class Consumer implements Runnable {

  private BlockingQueue queue;

  public Consumer(BlockingQueue queue) {
    this.queue = queue;
  }

  @Override
  public void run() {

    for (int i = 0; i < 50; i++) {
      Integer item = queue.dequeue();
    }
  }
}
