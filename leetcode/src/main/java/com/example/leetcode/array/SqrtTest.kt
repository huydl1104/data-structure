package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : x的平方根
 * 题目：在不使用sqrt（x）的情况喜爱，得到x的平方根的整数部分
 * 考察：二分法、牛顿迭代
 *
 */
fun main() {
    val index = sqrt1(11.toDouble(),11)
    println(index)
}

/**
 * 使用二分法
 */
fun sqrt(x:Int):Int{
    var left = 0
    var right = x
    var index = 0
    while (left <= right){
        val mid = left + (right - left) / 2
        println("mid ->$mid")
        if (mid * mid <= x){
            index = mid
            left = mid + 1
        }else{
            right = mid - 1
        }
    }
    return index
}

/**
 * 牛顿迭代
 */
fun sqrt1(i :Double,x: Int): Int {
    val res = (i + x/i) / 2
    if (res == i){
        return i.toInt()
    }else{
        sqrt1(res,x)
    }
    return res.toInt()
}

