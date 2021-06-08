package com.yudl.sturcture.xishuArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author yudongliang
 * create time 2021-05-26
 * describe : 稀疏数组
 * 思路：
 * 原数组 -> 稀疏数组
 *      遍历原始的二维数组，得到有效数据的个数 sum
 *      根据sum来创建 稀疏数组的行  sparseArr int[sum+1][3]
 *      将二维数组的有效数据存入 稀疏数组中。
 * 稀疏数组 -> 原数组
 *      先读取 稀疏数组的第一行，根据第一行的原始数据创建原始的二维数组，
 *      取出稀疏数组中的有效数据， 存如 给 原始的二维数组中。
 */
public class xishuArray {

    public static void main(String[] args) {
        int row = 11,column = 11;
        int[][] arr = new int[row][column];
        arr[1][1] = 1;
        arr[4][4] = 2;
        int count = 0;
        //遍历 得到 有效的数字
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    count++;
                }
            }
        }
        System.out.printf("count %d \n",count);
        int[][] sparseArr = new int[count+1][3];

        sparseArr[0][0] = row;
        sparseArr[0][1] = column;
        sparseArr[0][2] = count;

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0){
                    index ++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = arr[i][j];
                    System.out.printf("有效的数字 %d \t",arr[i][j]);

                }
            }
        }
        System.out.println();
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("结果 %d",anInt);
            }
            System.out.println();
        }

        int[][] srcArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        srcArr[sparseArr[1][0]][sparseArr[1][1]] = sparseArr[1][2];
        srcArr[sparseArr[2][0]][sparseArr[2][1]] = sparseArr[2][2];
        System.out.println("-------------------");
        for (int[] ints : srcArr) {
            for (int anInt : ints) {
                System.out.printf("src %d\t",anInt);
            }
            System.out.println();
        }
    }

}
