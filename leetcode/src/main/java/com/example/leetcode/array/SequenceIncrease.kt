package com.example.leetcode.array

import kotlin.math.max

/**
 * @author yudongliang
 * create time 2021-07-07
 * describe : 最长的连续递增序列序列
 * 题目：给一个未排序的数组，找到最长连续递增的序列，并返回序列的长度
 *
 */
fun main() {
    val array = arrayOf(1,2,3,4,5,6,6,10)
    val maxLength = maxLength(array)
    println(maxLength)
}

fun maxLength(array: Array<Int>): Int {
    var start = 0
    var max = 0
    for (i in 1 until array.size){
        if (array[i-1] >= array[i]){
            start = i
        }
        max =Math.max(max,i-start+1)
    }
    return max
}


