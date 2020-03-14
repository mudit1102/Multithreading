package com.java.multithreading;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MaximumInMatrix {

  public static void main(String[] args) {
    int[][] arr = {{17, -2, 20, 87}, {-3, 40, 70, 8}, {90, 78, 45, 39}, {12, 20, 11, 6, 89}};
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    ProcessMatrix processMatrix[] = new ProcessMatrix[4];
    ArrayList<Integer> rowMaximumList = new ArrayList<>();
    for(int i=0;i<4;i++)
    {
      processMatrix[i] = new ProcessMatrix(i,4,arr);
      Future<Integer> rowMaximum = executor.submit(processMatrix[i]);
      try {
        rowMaximumList.add(rowMaximum.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    System.out.println(rowMaximumList.stream().mapToInt(v->v).max());
    executor.shutdown();
  }
}