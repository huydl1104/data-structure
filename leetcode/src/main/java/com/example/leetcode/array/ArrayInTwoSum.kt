package com.example.leetcode.array

import com.example.leetcode.extension.printArray

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 两个数的和
 * 题目：
 * 1、给定一个整数数组 members ，从数组中找到 x1 + x2 = target，（不可以重复使用相同的元素，返回两个数的下标，以数组的形式返回
 * 2、给定一个升序排列的整数数组 members，从数组中找到 x1 + x2 = target，（不可以重复使用相同的元素，返回两个数的下标，以数组的形式返回
 */
fun main() {
    //第一题
    val array1 = arrayOf(1,4,6,7,89,100,23,14)
    val target = 189
    val solution1 = solution1(array1, target)
    solution1?.printArray()
    //第二题
    val array2 = arrayOf(1,4,6,7,14,23,89,100)
    val solution2 = solution2(array2, target)
    val solution3 = solution3(array2, target)
    solution2?.printArray()
    solution3?.printArray()
}

/**
 * 1、时间复杂度 On ，空间复杂度 On
 */
fun solution1(array: Array<Int>,target:Int):Array<Int>?{
    val map = HashMap<Int,Int>()
    for (index in array.indices){
        val value = array[index]
        if (map.containsKey(target - value)){
            return arrayOf(map[target - value]!!,index)
        }
        map[value] = index
    }
    return null
}

/**
 * 2、根据升序数组使用二分查找法，
 */
fun solution2(array: Array<Int>,target:Int):Array<Int>?{
    val length = array.size
    for (i in array.indices){
        var left = 0
        var right = length - 1
        while (left <= right){
            val mid = left + (right - left) / 2
            when {
                array[mid] == target - array[i] -> {
                    return arrayOf(i,mid)
                }
                array[mid] > target - array[i] -> {
                    right = mid - 1
                }
                else -> {
                    left = mid + 1
                }
            }
        }

    }
    return null
}

fun solution3(array: Array<Int>,target:Int):Array<Int>?{
    var left = 0
    var right = array.size - 1
    while (left <= right){
        val sum = array[left] + array[right]
        println("$left - ${array[left]} , $right - ${array[right]}  = $sum")
        when {
            sum == target -> {
                return arrayOf(left,right)
            }
            sum > target -> {
                right--
            }
            else -> {
                left++
            }
        }

    }
    return null
}
