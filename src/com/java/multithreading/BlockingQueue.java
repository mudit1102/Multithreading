package com.java.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

  private Queue<Integer> queue;
  private int capacity;

  public BlockingQueue(int capacity) {
    this.queue = new LinkedList<Integer>();
    this.capacity = capacity;
  }

  public synchronized  void enqueue(Integer item){
    while(queue.size() == capacity){
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    queue.add(item);
    System.out.println("Produced "+item);
    notify();
  }

  public synchronized Integer  dequeue(){
    while(queue.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Integer item = queue.poll();
    System.out.println("Consumed "+item);
    notify();
    return item;
  }

  public  synchronized boolean isEmpty(){
    return queue.size() == 0;
  }
}
