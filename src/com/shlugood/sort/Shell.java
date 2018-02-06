package com.shlugood.sort;

/*
 * 希尔排序是插入排序的改进；
 * 思路：
 *   插入排序（冒泡排序）是按步长为1进行上浮，希尔排序则是先按照较长的步长上浮，步长按一定序列进行收缩，最后按步长为1排序；
 * 时间复杂度N平方与线性对数之间；
 * 优点：
 *   1. 与高级排序（插入排序，快速排序）相比，性能相当；（除了对于很大的N，高级排序只会比希尔排序快两倍）；
 *   2. 实现非常简单，适合在无成熟工具时，自己手动写；
 * */
public class Shell {
    public static void sort(int[] arr){
        int N =  arr.length;
        int h = 1;
        while(h < N) h = h*3 +1;/*这个序列可以优化，但这个简单的序列已经很有效率了*/
        while(h >=1){
            for(int i = h; i <=N; i++){/*这里是一个步长为h的冒泡排序（插入排序）*/
                for(int j = i ; j >= h; j -=h){
                    if(SortUtil.less(arr[j - h], arr[j])) continue;
                    SortUtil.exch(arr, j, j -h);
                }
            }
            h = h/3;
        }
    }
}
