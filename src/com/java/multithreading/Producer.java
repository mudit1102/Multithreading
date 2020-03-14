package com.java.multithreading;

import java.util.Random;

public class Producer implements Runnable {

  private BlockingQueue queue;

  public Producer(BlockingQueue queue) {
    this.queue = queue;
  }


  @Override
  public void run() {

    Random random = new Random();
    for(int i=0;i<50;i++){
      Integer val = random.nextInt() % 50;

      queue.enqueue(val);

    }
  }
}
