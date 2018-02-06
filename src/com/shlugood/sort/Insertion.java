package com.shlugood.sort;

/*
 * 插入排序是一种初等排序方法；又名冒泡排序；
 * 思路：
 *   将第I个元素与之前的元素比较，如果比之前的元素小就与之交换，直到比前面的元素大；
 *   这种排序方法元素好像气泡一样冒上去，所以又叫冒泡排序；
 * 时间复杂度N平方；
 * 优点：
 *   1. 当序列中倒置元素很少时，是一种最快的排序方法；
 *   2. 比选择排序快一倍；
 * */

public class Insertion {
    public static void sort(int[] arr){
        int N = arr.length;
        for(int i = 0; i< N; i++){
            for(int j = i; j > 0; j--){
                if(SortUtil.less(arr[j- 1], arr[j])) break;
                SortUtil.exch(arr, j - 1, j);
            }
        }
    }
}
