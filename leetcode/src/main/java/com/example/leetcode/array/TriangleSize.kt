package com.example.leetcode.array

import java.util.*

/**
 * @author yudongliang
 * create time 2021-07-07
 * describe : 三角形的最大的长度
 * 题目：给定一个数组存放 一些 正数，返回三个长度组成的最大的周长，若是不能成为 三角形返回 0
 */
fun main() {
    val array = arrayOf(20,4,5,6,18,30)
    val maxLength = maxLength1(array)
    println(maxLength)
}



private fun maxLength1(array: Array<Int>):Int{
    Arrays.sort(array)
    var max = 0
    for (index in 2 until array.size){
        if (array[index-2] + array[index -1] > array[index]){
            max = max.coerceAtLeast(array[index - 2] + array[index - 1] + array[index])
        }
    }
    return max
}

