package com.java.multithreading.impl;

import com.java.multithreading.BlockingQueue;
import com.java.multithreading.Producer;
import java.util.Random;

public final class ProducerImpl implements Producer<Integer>, Runnable {

  private final BlockingQueue<Integer> queue;

  public ProducerImpl(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    insertItemToQueue();
  }

  @Override
  public void insertItemToQueue() {
    Random random = new Random();
    for (int i = 0; i < 50; i++) {
      Integer val = random.nextInt() % 50;
      queue.enqueue(val);
    }
  }
}
