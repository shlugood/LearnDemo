package com.shlugood.sort;

public class SortUtil {
    public static boolean less(int a, int b){
        return a <= b;
    }

    public static void exch(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
