package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 给出一组有序的数组，去除内部重复的元素，得到数组内部的长度
 * 空间复杂度 O（1） 级别， 不能才用 Set
 * 思路：
 *  采用双向指针的方案
 *
 */
fun main() {
    val array = arrayOf(1,2,2,3,3,3,4)
    val arrayLength = getArrayLength(array)
    println(arrayLength)
}


private fun getArrayLength(array: Array<Int>):Int{
    var i = 0
    var j = 1
    while (j < array.size){
         if (array[i] != array[j]){
            i++
            array[i] = array[j]
        }
        j++
    }
    return i + 1
}