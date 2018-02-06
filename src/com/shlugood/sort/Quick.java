package com.shlugood.sort;

/*
 * 快速排序是我要写的第二种高级排序；
 * 思路：
 *   与归并排序不同的是，归并排序是在数组拆分之后对小数组排序，快速排序则是先对数组进行一个合适的拆分。
 *   选任意一个元素（通常第一个），从正方向找回第一个大于这个元素的元素，从反方向找回第一个小于这个元素的元素；
 *   交换这两个元素，直到两头指针相遇，此时相遇位置作为分界点，前半部分小于该元素，后半部分大于该元素；
 *   递归进行；
 * 时间复杂度线性对数；
 * 性能特点：
 *   1. 对随机数组偏爱；对部分有序数组反而不好；
 *   2. 该排序算法是使用最广泛的排序算法；
 * */
public class Quick {
    public static void sort(int[] arr){
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int lo, int hi){
        if(lo>= hi) return;
        int partion = partition(arr, lo, hi);
        sort(arr, lo, partion);
        sort(arr, partion + 1, hi);
    }

    public static int partition(int[] arr, int lo, int hi){
        int i = lo, j = hi +1;
        int v = arr[lo];
        while(true){
            while(SortUtil.less(arr[++i], v)) if(i == hi) break;
            while(SortUtil.less(v, arr[++j])) if(j == lo) break;
            if(i>= j) break;
            SortUtil.exch(arr, i, j);
        }
        SortUtil.exch(arr, j, lo);
        return j;
    }
}
