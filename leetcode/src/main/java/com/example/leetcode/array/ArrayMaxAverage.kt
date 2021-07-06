package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-07-06
 * describe : 子数组的最大平均数
 * 题目：一个整数数组，找出平均最大且长度为k的下标连续的子数据，输出最大的平均数
 * 例如：【1，12，-5，-6。50，3】 k = 4
 */
fun main() {
    val arr = arrayOf(1,12,-5,-6,50,3)
    val k = 4
    val findMaxAverage = findMaxAverage(arr, k)
    println(findMaxAverage)
}

private fun findMaxAverage(arr: Array<Int>, k: Int): Double {
    var sum = 0
    for (i in 0 until k){
        sum += arr[i]
    }
    var max = sum
    var i = k
    while (i < arr.size){
        sum = sum + arr[i] - arr[i-k]
        if (sum > max){
            max = sum
        }
        i++
    }
    return max / k * 1.0
}