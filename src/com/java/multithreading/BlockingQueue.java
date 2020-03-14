package com.java.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public final class BlockingQueue<T> {

  private Queue<T> queue;
  private int capacity;

  public BlockingQueue(int capacity) {
    this.queue = new LinkedList<T>();
    this.capacity = capacity;
  }

  public synchronized  void enqueue(T item){
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

  public synchronized T  dequeue(){
    while(queue.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    T item = queue.poll();
    System.out.println("Consumed "+item);
    notify();
    return item;
  }

  public  synchronized boolean isEmpty(){
    return queue.size() == 0;
  }
}
