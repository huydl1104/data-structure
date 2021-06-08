package com.yudl.sturcture.feibonaqie

import java.util.*

/**
 * @author yudongliang
 * create time 2021-06-07
 * describe : 斐波那契数列
 */
object FibonacciKotlin {
    var maxSize = 20
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1, 8, 10, 89, 100, 1000)
        println(fibSearch(arr, 1000))
    }

    //斐波那契数列
    private fun fib(): IntArray {
        val f = IntArray(maxSize)
        f[0] = 1
        f[1] = 1
        for (i in 2 until maxSize) {
            f[i] = f[i - 1] + f[i - 2]
        }
        return f
    }

    private fun customPri(arr:IntArray){
        for (value in arr){
            print("$value \t")
        }
        println()
    }

    private fun fibSearch(a: IntArray, key: Int): Int {
        var low = 0
        var high = a.size - 1
        var k = 0
        var mid = 0
        val f = fib()
        customPri(f)
        while (high > f[k] - 1) {
            k++
        }
        val temp = Arrays.copyOf(a, f[k])
        customPri(temp)
        for (i in high + 1 until temp.size) {
            temp[i] = a[high]
        }
        customPri(temp)
        while (low <= high) {
            mid = low + f[k - 1] - 1
            if (key < temp[mid]) {
                high = mid - 1
                k--
            } else if (key > temp[mid]) {
                low = mid + 1
                k -= 2
            } else {
                return if (mid <= high) {
                    mid
                } else {
                    high
                }
            }
        }
        return -1
    }
}