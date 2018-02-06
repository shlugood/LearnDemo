package com.shlugood.sort;

/*
* 选择排序是一种初等排序方法；
* 思路：
*   每次查询出从i以后所有元素的最小元素，将该元素与i交换位置；
* 时间复杂度N平方；
* 优点：
*   1. 运行时间与输入无关；
*   2. 数据移动是最少的（N次数据移动）；
* */
public class Selection {
    public static void sort(int[] arr){
        int N = arr.length;
        for(int i = 0; i< N; i++){
            int min = i;
            for(int j = i+1; j < N; j++){
                if(SortUtil.less(arr[min], arr[j])) continue;
                min = j;
            }
            SortUtil.exch(arr, i, min);
        }
    }
}
