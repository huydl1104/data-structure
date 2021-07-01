package com.example.leetcode.array

import com.example.leetcode.extension.printArray
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 整形无序数组 nums，求数组中的三个最大数的乘积
 * 考察：线性扫描
 */
fun main() {
    val array = arrayOf(1,3,4,5,-6,-8)
    val productSort1 = productSort(array)
    val productSort2 = getMaxMin(array)
    println(productSort1)
    println(productSort2)
}

/**
 * 暴力破解
 */
fun productSort(array:Array<Int>): Int {
    Arrays.sort(array)
    array.printArray()
    val len = array.size
    return max(array[0]*array[1]*array[len-1] ,array[len-1]*array[len-2]*array[len-3])
}

/**
 * 遍历数组，找到两个最小的，三个最大的值，进行相乘
 */
fun getMaxMin(nums:Array<Int>):Int{
    var min1 = Integer.MAX_VALUE
    var min2 = Integer.MAX_VALUE
    var max1 = Integer.MIN_VALUE
    var max2 = Integer.MIN_VALUE
    var max3 = Integer.MIN_VALUE
    for (x in nums){
        if (x < min1){
            min2 = min1
            min1 = x
        }else if (x < min2){
            min2 = x
        }
        if (x > max1){
            max3 = max2
            max2 = max1
            max1 = x
        }else if (x > max2){
            max3 = max2
            max2 = x
        }else if (x > max3){
            max3 = x
        }
    }
    return max(min1*min2*max1,max1*max2*max3)
}




