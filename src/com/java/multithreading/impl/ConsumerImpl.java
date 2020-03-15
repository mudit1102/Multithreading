package com.java.multithreading.impl;

import com.java.multithreading.BlockingQueue;
import com.java.multithreading.Consumer;

public final class ConsumerImpl implements Consumer<Integer>, Runnable {

  private final BlockingQueue<Integer> queue;

  public ConsumerImpl(BlockingQueue<Integer> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    getItemFromQueue();
  }

  @Override
  public void getItemFromQueue() {
    for (int i = 0; i < 50; i++) {
      Integer item = queue.dequeue();
    }
  }
}
