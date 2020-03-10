package com.java.multithreading;

import java.util.ArrayList;
import java.util.Random;

public class MergeSort {
  public static void main(String[] args) throws InterruptedException {
    Random random = new Random();
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add(random.nextInt() % 100);
    }

    ArrayList<Integer> leftList = new ArrayList<>(list.subList(0, list.size() / 2));
    ArrayList<Integer> rightList = new ArrayList<>(list.subList(list.size() / 2, list.size()));

    Worker thread1 = new Worker(leftList);
    Worker thread2 = new Worker(rightList);

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    list.clear();
    finalMerge(list, leftList, rightList);
    System.out.println(list);
  }

  private static void finalMerge(ArrayList<Integer> originalList, ArrayList<Integer> leftList,
      ArrayList<Integer> rightList) {

    int leftIndex = 0, rightIndex = 0;
    int leftSize = leftList.size();
    int rightSize = rightList.size();
    int originalSize = originalList.size();
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
