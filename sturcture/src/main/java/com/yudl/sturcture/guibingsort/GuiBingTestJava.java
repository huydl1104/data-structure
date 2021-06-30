package com.yudl.sturcture.guibingsort;

/**
 * @author yudongliang
 * create time 2021-05-21
 * describe : 归并排序是 先拆分在合并的过程
 */
public class GuiBingTestJava {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 7, 1};
        sort(arr,0,arr.length-1);
        //1、0-4
        //2、0-2
        //3、0-1
        //4、2-4
        //4、3-4
    }

    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
//            customPrint(a);
            //左右归并
            merge(a,low,mid,high);
        }

        return a;
    }


    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i = low;
        int j = mid+1;
        int k = 0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }

    public static void customPrint(int[] arr){
        for (int i : arr) {
            System.out.print(i+"\t");
        }
        System.out.println("-------");
    }


}



