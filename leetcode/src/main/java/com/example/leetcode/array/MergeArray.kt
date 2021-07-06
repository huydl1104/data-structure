package com.example.leetcode.array

import java.util.*

/**
 * @author yudongliang
 * create time 2021-07-06
 * describe : 合并两个有序数组 nums1 和 nums2，将nums2合并到nums1中，容量分别为 m 和 n ，假设 nums1的空间大小为 m+n
 */
fun main() {
    val arr1 = arrayOf(1,3,5,7,9,0,0,0,0,0)
    val arr2 = arrayOf(2,4,6,8,10)
//    val mergeArray = merge1(nums1 = arr1,5,nums2 = arr2,arr2.size)
    val mergeArray1 = merge2(nums1 = arr1,5,nums2 = arr2,arr2.size)
    println(mergeArray1.contentToString())
}


private fun merge1(nums1: Array<Int>, m: Int, nums2: Array<Int>, n: Int): Array<Int> {
    System.arraycopy(nums2,0,nums1,m,n)
    Arrays.sort(nums1)
    return nums1
}

private fun merge2(nums1: Array<Int>, m: Int, nums2: Array<Int>, n: Int): Array<Int> {
    var p1 = m - 1
    var p2 = n - 1
    var p = m + n - 1// nums1 的最后一个下标
    while ( p1 >= 0 && p2 >= 0){
        nums1[p--] = if (nums1[p1] < nums2[p2]) nums2[p2--] else nums1[p1--]
    }
    return nums1
}