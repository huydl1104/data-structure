package com.yudl.sturcture.xierSort;

/**
 * @author yudongliang
 * create time 2021-06-03
 * describe :
 */
public class XiErSortTest {


    public static void main(String[] args) {
        int[] arr = new int[]{3, 7, 1, 4, 5, 0,10,35,60,23,44};
        xiErSort(arr);
        customPrint(arr);
    }

    private static void customPrint(int[] arr){
        for (int value : arr) {
            System.out.print(value+"\t");
        }
        System.out.println();
    }

    public static void xiErSort(int[] array){
//        int temp;
        for (int k = array.length / 2; k > 0; k /= 2) {
            for (int i = k; i < array.length; i++) {
                //1、这种类似于冒泡 多次交换
//                for (int j = i; j >= k; j -= k) {
//                    if (array[j - k] > array[j]) {
//                        temp = array[j - k];
//                        array[j - k] = array[j];
//                        array[j] = temp;
//                    }
//                }
                //2、使用插入排序解决
                int j = i;
                int temp = array[j];
                while (j - k >= 0 && array[j-k] > temp){
                    array[j] = array[j-k];
                    j -= k;
                }
                array[j] = temp;
            }
        }

    }

}
