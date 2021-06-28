package com.yudl.sturcture.heapSort1

/**
 * @author yudongliang
 * create time 2021-06-09
 * describe :
 */
object HeapSortKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(13, 7, 8, 3, 29, 6, 1)
        heapSort(arr)
    }

    //时间复杂度为 O(nlogn)级别
    private fun heapSort(arr: IntArray) {
        //找到最左侧的非叶子节点
        for (k in arr.size / 2 - 1 downTo 0) {
            adjustHeap(arr, k, arr.size)
        }
        println("-----------")
        //执行完毕后 最大值在第一位
        var temp: Int
        for (i in arr.size - 1 downTo 1) {
            temp = arr[0]
            arr[0] = arr[i]
            arr[i] = temp
            adjustHeap(arr, 0, i)
        }
        for (i in arr) {
            print(i.toString() + "\t")
        }
        println()
    }

    /**
     * 堆排序
     * arr：待排序的数组
     * index：index的位置
     * length：数组的长度
     */
    private fun adjustHeap(arr: IntArray, index: Int, length: Int) {
        var i = index
        val temp = arr[index]
        var k = i * 2 + 1 //找到左节点
        while (k < length) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++
            }
            if (arr[k] > temp) {
                arr[i] = arr[k]
                i = k
            } else {
                break
            }
            k = k * 2 + 1
        }
        arr[i] = temp

        for (value in arr){
            print("${value}\t")
        }
        println(" $i , $k , $index")
    }
}