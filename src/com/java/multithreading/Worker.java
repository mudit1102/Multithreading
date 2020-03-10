package com.java.multithreading;

import java.util.ArrayList;

public class Worker extends Thread{
  private ArrayList<Integer> list;

  Worker(ArrayList<Integer> list) {
    this.list = list;
  }

  public ArrayList<Integer> getList() {
    return list;
  }

  public void merge(ArrayList<Integer> arrayList, int low, int mid, int high) {
    ArrayList<Integer> sortedList = new ArrayList<>();
    int i = low;
    int j = mid + 1;

    while (i <= mid && j <= high) {
      if (arrayList.get(i) <= arrayList.get(j)) {
        sortedList.add(arrayList.get(i));
        i++;
      } else {
        sortedList.add(arrayList.get(j));
        j++;
      }
    }

    while (i <= mid) {
      sortedList.add(arrayList.get(i));
      i++;
    }

    while (j <= high) {
      sortedList.add(arrayList.get(j));
      j++;
    }

    for (int ind = 0, k = low; ind < sortedList.size(); ++ind, ++k) {
      arrayList.set(k, sortedList.get(ind));
    }

    sortedList.clear();
  }

  public void mergeSort(ArrayList<Integer> arrayList, int low, int high) {
    if (low >= high) {
      return;
    }

    int mid = low + (high - low) / 2;
    mergeSort(arrayList, low, mid);
    mergeSort(arrayList, mid + 1, high);
    merge(arrayList, low, mid, high);

  }

  public void run() {
    mergeSort(list, 0, list.size() - 1);
  }
}
