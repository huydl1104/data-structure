package com.yudl.sturcture.kuaipaisort;

/**
 * @author yudongliang
 * create time 2021-05-20
 * describe : 快速排序
 */
public class TestJava {

    public static void main(String[] args) {
        TestJava testJava = new TestJava();
        int[] arr = new int[]{8, 2, 7, 11, 15, 1, 0, 0, 21,3,4};
        testJava.QSort(arr,0,arr.length - 1);
        for (int i : arr) {
            System.out.println(" it ->"+i);
        }
    }

    public void QSort(int[] a, int left, int right) {
        if(left >= right) {
            return;
        }
        int base = median3(a, left, right);
        int i = left;
        int j = right - 1;
        while(i < j) {
            while(i < j && base > a[++i]) {}
            while(i < j && base < a[--j]) {}
            if(i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, right - 1);
        QSort(a, left, i - 1);
        QSort(a, i + 1, right);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private int median3(int[] a,int i,int j) {
        int m = (i + j) >> 1;
        if (a[m] < a[i]) {
            swap(a, i, m);
        }
        if (a[j] < a[i]) {
            swap(a, i, j);
        }
        if (a[j] < a[m]) {
            swap(a, j, m);
        }
        //将枢纽元放在j - 1;
        swap(a, m, j - 1);
        return a[j - 1];
    }

}
