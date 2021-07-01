package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 斐波那契数列
 * 题目：求取 斐波那契数列 第n为的值
 * 斐波那契：每一位的值等于前两位数字之和，前两位数固定为 0，1
 *  0,1,1,2,3,5,8,13,21,34,55
 */
fun main() {
    //第一种方案
    val n = 10
    val solution1 = solution1(n)
    println(solution1)

    //第二种方案 使用数组储存起来已经计算过的数据，
    val array = Array(n+1){0}
    val solution2 = reverse(array,n)
    println(solution2)

    //第三种方案 使用双指针的方案 将空间复杂度降低
    val solution3 = solution3(10)
    println(solution3)
}

private fun solution1(n: Int):Int{
    if (n == 0){
       return 0
    }
    if (n == 1 || n == 2){
       return 1
    }
    return solution1(n - 1) + solution1(n - 2)
}

private fun reverse(arr:Array<Int>,n: Int):Int{
    if (n == 0){
        return 0
    }
    if (n == 1 || n == 2){
        return 1
    }
    if (arr[n] != 0){
        return arr[n]
    }
    arr[n] = reverse(arr,n - 1) + reverse(arr,n - 2)
    return arr[n]
}

private fun solution3(n :Int): Int {
    if (n == 0){
        return 0
    }
    if (n == 1 || n == 2){
        return 1
    }
    var left = 0
    var right = 1
    var i = 2
    var sum = 0
    while (i <= n){
        sum = left +right
        left = right
        right = sum
        i++
    }
    return right
}
