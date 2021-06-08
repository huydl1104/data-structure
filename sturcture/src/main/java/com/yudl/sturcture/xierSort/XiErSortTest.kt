package com.yudl.sturcture.xierSort

/**
 * @author yudongliang
 * create time 2021-06-03
 * describe : 希尔排序
 *  思路：使用分组的思想，从 gap 的位置向后遍历，这里可以借鉴 插入排序的算法思想
 */

fun main() {
    val arr = arrayOf(3, 7, 1, 4, 5, 0, 10, 35, 60, 23, 44)
    xiErSort(arr)
    customPrint(arr)
}


/**
 *  核心思想是 分组
 */
fun xiErSort(array: Array<Int>) {
//        int temp;
    var k = array.size / 2
    while (k > 0) {
        for (i in k until array.size) {
            //1、这种类似于冒泡 多次交换,效率不高
//                for (int j = i; j >= k; j -= k) {
//                    if (array[j - k] > array[j]) {
//                        temp = array[j - k];
//                        array[j - k] = array[j];
//                        array[j] = temp;
//                    }
//                }
            //2、使用插入排序解决，减少了数据交换的次数
            var j = i
            val temp = array[j]
            while (j - k >= 0 && array[j - k] > temp) {
                array[j] = array[j - k]
                j -= k
            }
            array[j] = temp
        }
        k /= 2
    }
}


/**
 * 交换两个角标的数据
 */
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

private fun xierSort() {
    val arr = intArrayOf(23, 12, 99, 88, 14, 55, 26, 3)
    val len = arr.size
    var gap = len
    var i: Int
    var j: Int
    var k: Int
    var temp: Int
    while (true) {
        gap = gap shr 1
        i = 0
        while (i < gap) {
            j = i + gap
            while (j < len) {
                k = j
                while (k > i) {
                    if (arr[k] < arr[k - gap]) {
                        temp = arr[k - gap]
                        arr[k - gap] = arr[k]
                        arr[k] = temp
                    } else {
                        break
                    }
                    k -= gap
                }
                j += gap
            }
            i++
        }
        if (gap == 1) {
            break
        }
    }
    Log.i("yyyyy", "index --> $arr")
}*/
