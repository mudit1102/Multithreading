package com.java.multithreading;

import java.util.concurrent.Callable;

public class ProcessMatrix implements Callable {

  int row;
  int columns;
  int mat[][];

  public ProcessMatrix(int row, int columns, int[][] mat) {
    this.row = row;
    this.columns = columns;
    this.mat = mat;
  }

  @Override
  public Integer call() throws Exception {
    int maximum = Integer.MIN_VALUE;
    for(int i=0;i<columns;i++)
    {
      if(maximum < mat[row][i])
        maximum = mat[row][i];
    }
    return maximum;
  }
}

