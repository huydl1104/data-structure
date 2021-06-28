package com.yudl.sturcture.heapSort1;

/**
 * @author yudongliang
 * create time 2021-06-09
 * describe :
 */
public class HeapSortJava {

    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        heapSort(arr);
    }

    //时间复杂度为 O(nlogn)级别
    public static void heapSort(int[] arr){
        //找到最左侧的非叶子节点
        for (int k = arr.length /2 -1 ;k >= 0; k--){
            adjustHeap(arr,k,arr.length);
        }
        //执行完毕后 最大值在第一位
        int temp ;
        for (int i = arr.length -1;i>0;i--){
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeap(arr,0,i);
        }
        for (int i : arr) {
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void adjustHeap(int[] arr,int i ,int length){
        int temp = arr[i];
        int k = i * 2 + 1;//找到左节点
        for (;k<length; k = k * 2 +1){
            if (k + 1 < length && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
