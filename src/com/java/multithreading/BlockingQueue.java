package com.java.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public final class BlockingQueue<T> {

  private Queue<T> queue;
  private int capacity;
  private volatile int size;

  public BlockingQueue(int capacity) {
    this.queue = new LinkedList<T>();
    this.capacity = capacity;
    this.size = 0;
  }

  public void enqueue(T item) {

    synchronized (this) {
      while (size == capacity) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    if (size < capacity) {
      synchronized (this) {
        while (size == capacity) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        queue.add(item);
        System.out.println("Produced item " + item);
        size++;
        notify();
      }
    }
  }

  public T dequeue() {

    T item = null;
    synchronized (this) {
      while (size == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    if (size > 0) {
      synchronized (this) {
        while (size == 0) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        item = queue.poll();
        System.out.println("Consumed item " + item);
        size--;
        notify();
      }
    }

    return item;
  }

  public synchronized boolean isEmpty() {
    return queue.size() == 0;
  }
}
