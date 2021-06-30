package com.yudl.sturcture.digui;

import android.os.Build;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yudongliang
 * create time 2021-06-01
 * describe : 走迷宫
 */
public class TestJava {
    public static void main(String[] args) {
        TestJava java = new TestJava();
        int[][] intArray = java.createIntArray();
        java.setWay(intArray,1,1);
        System.out.println("--------------------------------");
        java.print(intArray);
        int[] arr = new int[]{5,6,9,11,14,10,10,11};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Arrays.stream(arr).sum();
        }
    }


    //0:该点没有走过
    //1:墙
    //2:已经走过
    //3:该位置不通
    //下 右 上 左
    boolean setWay(int[][] arr, int i, int j){
        if (arr[6][5] == 2){
            return true;
        }else{
            if (arr[i][j] == 0){
                arr[i][j] = 2;
                if (setWay(arr,i+1,j)){
                    return true;
                }else if (setWay(arr, i, j+1)){
                    return true;
                }else if (setWay(arr, i-1, j)){
                    return true;
                }else if (setWay(arr, i, j-1)){
                    return true;
                }else {
                    arr[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }

    }

    int[][] createIntArray(){
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[4][3] = 1;
        print(map);
        return map;
    }
    void print(int[][] map){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
