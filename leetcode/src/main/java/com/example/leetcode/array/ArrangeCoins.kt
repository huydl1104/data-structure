package com.example.leetcode.array

/**
 * @Author: ydl
 * @Description: 排列硬币
 * @CreateDate: 2021-07-04
 * 题目：总共有n枚硬币，将他们呢排列到一个阶梯形状，第k行就必须有k枚硬币。
 *   给定一个n找出形成完整的接替行的总行数。
 */
fun main() {
    val n = 10
    val arrangeCoins1 = arrangeCoins(n)
    val arrangeCoins2 = arrangeCoins1(n)
    println(arrangeCoins1)
    println(arrangeCoins2)
}

//暴力
fun arrangeCoins(n : Int): Int {
    var num = n
    for (i in 1..num){
        num -= i
        if (num <= i){
            return i
        }
    }
    return 0
}

//使用双指针
fun arrangeCoins1(n: Int): Int {
    var low = 0
    var high = n
    while (low <= high){
        val mid = low + (high - low) / 2
        val const = (mid + 1) * mid / 2
        if (const == n){
            return mid
        }else if (const > n){
            high = mid - 1
        }else{
            low = mid + 1
        }
    }
    return 0
}
