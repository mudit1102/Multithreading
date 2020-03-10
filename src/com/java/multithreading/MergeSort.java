package com.java.multithreading;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;


public class MergeSort {

  public static void main(String[] args) throws InterruptedException {
    Random random = new Random();
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add(random.nextInt() % 100);
    }

    System.out.println(list);

    ArrayList<Integer> leftList = new ArrayList<>(list.subList(0, list.size() / 2));
    ArrayList<Integer> rightList = new ArrayList<>(list.subList(list.size() / 2, list.size()));

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    ArrayList<Future<ArrayList<Integer>>> resultList = new ArrayList<>();

    Worker leftWorker = new Worker(leftList);
    Future<ArrayList<Integer>> leftResult = executor.submit(leftWorker);
    resultList.add(leftResult);

    Worker rightWorker = new Worker (rightList);
    Future<ArrayList<Integer>> rightResult = executor.submit(rightWorker);
    resultList.add(rightResult);

    Future<ArrayList<Integer>> futureLeft = resultList.get(0);
    try {
      leftList = futureLeft.get();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    Future<ArrayList<Integer>> futureRight = resultList.get(1);
    try {
      rightList = futureRight.get();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    list.clear();
    finalMerge(list, leftList, rightList);
    System.out.println(list);
    executor.shutdown();
  }

  private static void finalMerge(ArrayList<Integer> originalList, ArrayList<Integer> leftList,
      ArrayList<Integer> rightList) {

    int leftIndex = 0, rightIndex = 0;
    int leftSize = leftList.size();
    int rightSize = rightList.size();
    while (leftIndex < leftSize && rightIndex < rightSize) {
      if (leftList.get(leftIndex) <= rightList.get(rightIndex)) {
        originalList.add(leftList.get(leftIndex));
        leftIndex++;
      } else {
        originalList.add(rightList.get(rightIndex));
        rightIndex++;
      }
    }

    while (leftIndex < leftSize) {
      originalList.add(leftList.get(leftIndex));
      leftIndex++;
    }

    while (rightIndex < rightSize) {
      originalList.add(rightList.get(rightIndex));
      rightIndex++;
    }

  }
}
