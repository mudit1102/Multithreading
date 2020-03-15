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

  public void enqueue(T item) {

    synchronized (this) {
      while (queue.size() == capacity) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    synchronized (this) {
      while (queue.size() == capacity) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      queue.add(item);
      System.out.println("Produced item " + item);
      notifyAll();
    }

  }

  public T dequeue() {

    T item = null;
    synchronized (this) {
      while (queue.size() == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    synchronized (this) {
      while (queue.size() == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      item = queue.poll();
      System.out.println("Consumed item " + item);
      notifyAll();
    }

    return item;
  }

  public synchronized boolean isEmpty() {
    return queue.size() == 0;
  }
}
