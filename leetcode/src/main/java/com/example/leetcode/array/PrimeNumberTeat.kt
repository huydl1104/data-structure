package com.example.leetcode.array

import kotlin.math.sqrt

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 求1到n之间有多少哥素数
 * 素数：是 只有 1 和 本身 。
 * 合数：除了 1 和 本身 还有其他的数
 */
fun main() {

    val prime = countPrime(100)
    println(prime)
}

/**
 * 统计素数的个数
 */
fun countPrime(n :Int): Int {
    var count = 0
    val flags = Array(n){false}
    for (i in 2 until n){
        if (!flags[i]){
            count ++
            var j = i * i
            while (j < n){
                flags[j] = true
                j += i
            }
        }
    }
    return count
}

/**
 * 判断是否为素数（质数）
 */
fun isPrime(num:Int): Boolean {
    var i = 2
    while (i * i <= num){
        if (num % i == 0){
            return false
        }
        i++
    }
    return true
}

