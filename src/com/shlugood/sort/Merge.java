package com.shlugood.sort;

/*
 * 归并排序是我要写的第一种高级排序；
 * 思路：
 *   将一个大数组拆分成两个数组；
 *   将两个数组分别排序；
 *   排序后将两个数组合并；
 *   递归进行；
 * 时间复杂度线性对数；
 * 两种实现策略：
 *   1. 自顶向下；
 *   2. 自底向上；更适合用链表组织数据；
 * */
public class Merge {
    private static int[] aux;

    public static void sort(int[] arr){
        aux = new int[arr.length];//这个要一次性分配内存，重复利用；
        sort(arr, 0, arr.length -1);
    }

    private static void sort(int[] arr, int lo, int hi){
        if(lo == hi) return;//对于只有一个元素时，递归返回
        int mid = lo + (hi - lo)/2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private static void merge(int[] arr, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo; k < hi; k++) aux[k] = arr[k];
        for(int k = 0; k < aux.length; k++){
            if(i>mid) arr[k] = aux[j++];
            else if(j > hi) arr[k] = aux[i++];
            else if(SortUtil.less(aux[i], aux[j])) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }
}
