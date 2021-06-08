package com.yudl.sturcture.xuanzesort

/**
 * @author yudongliang
 * create time 2021-06-03
 * describe : 选择排序
 *      思路：选择排序将数组分成已排序区间和未排序区间。初始已排序区间为空。
 *      每次从未排序区间中选出最小的元素插入已排序区间的末尾，直到未排序区间为空。
 *    是在冒泡的排序的基础上进行优化
 *    先选取第一个为最小的值，在遍历的过程中去 比较，保留最小值的角标，每轮遍历完成去 交换数据，达到优化的目的
 */
fun main() {
    val arr = arrayOf(3, 7, 1, 4, 5, 0,10,35,60,23,44)
    selectSort(arr)
    customPrint(arr)
}

/**
 * 选择排序
 *    在冒泡排序的基础上进行优化，每轮遍历的过程中找到最小的角标，并在结束的时候进行交换数据的操作，
 *    极大的减少了交换数据的次数提高了效率
 */
private fun selectSort(arr: Array<Int>){
    for (i in 0 until arr.size -1 ){
        //每一轮中找到值值最小的角标
        var minIndex = i
        for (j in i+1 until arr.size){
            if (arr[minIndex] > arr[j]){
                minIndex = j
            }
        }
        if (minIndex != i){
            swap(arr, minIndex, i)
        }
    }
}

private fun swap(arr: Array<Int>, i: Int, j: Int){
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

/**
 * 打印数据中的数据
 */
private fun customPrint(arr: Array<Int>){
    for (value in arr){
        print("$value \t")
    }
    println()
}

/*
fun select_sort(array: IntArray, lenth: Int) {
    for (i in 0 until lenth - 1) {
        var minIndex = i
        for (j in i + 1 until lenth) {
            if (array[j] < array[minIndex]) {
                minIndex = j
            }
        }
        if (minIndex != i) {
            val temp = array[i]
            array[i] = array[minIndex]
            array[minIndex] = temp
        }
    }
}  */
