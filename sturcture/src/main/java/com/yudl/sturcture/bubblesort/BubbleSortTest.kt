package com.yudl.sturcture.bubblesort

import java.net.Inet4Address

/**
 * @author yudongliang
 * create time 2021-06-03
 * describe : 冒泡排序
 */

fun main() {
//    val arr = arrayOf(3, 7, 1, 4, 5, 0)
    var arr = createRandomArray(100)
//    customPrint(arr)
    val start = System.currentTimeMillis()
    customBubbleSort(arr)
    val end = System.currentTimeMillis()
    println("gap ->${end - start}")
    customPrint(arr)
}

private fun createRandomArray(n:Int):Array<Int>{
    val arr : Array<Int> = Array(n){0}
    for (i in 0 until n){
        arr[i] = (Math.random() * n).toInt()
    }
    return arr
}

/**
 * 相邻的两个数进行比较， 左的值大，右侧的值小， 两者交换位置
 *  双for 循环来解决遍历的问题
 *  1、对外侧的一个循环是 指 跑几圈
 *  2、内部的循环是 比较相邻的两个数的大小
 *  例如： {3,7,1,4,5,0} 共有 6个数据，也就是 执行 arr.size - 1 圈就可以
 *
 */
private fun customBubbleSort(arr: Array<Int>){
    var isChange:Boolean
    for (i in 0 until arr.size - 1){
        isChange = false
        for (j in i+1 until arr.size){
            if (arr[i] > arr[j]){
                swap(arr, i, j)
                isChange = true
            }
        }
        if (!isChange){
            break
        }
    }
}
private fun swap(arr: Array<Int>, i: Int, j: Int){
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}
private fun customPrint(arr: Array<Int>){
    for (value in arr){
        print("$value \t")
    }
    println()
}

/*
private fun bubbleSort(arr: IntArray) {
    val start = System.currentTimeMillis()
    val len = arr.size
    var isChange: Boolean
    for (i in 0 until len - 1) {
        isChange = false
        for (j in i + 1 until len) {
            if (arr[i] > arr[j]) {
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                isChange = true
            }
        }
        if (!isChange) {
            break
        }
    }
    val end = System.currentTimeMillis()
}*/
