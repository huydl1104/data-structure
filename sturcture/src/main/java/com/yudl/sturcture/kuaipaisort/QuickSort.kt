package com.yudl.sturcture.kuaipaisort

/**
 * @author yudongliang
 * create time 2021-05-18
 * describe :  假设给你一组无序的数字，找到其中排名第 k 位的数字
 * 思路：
 *      先进行快排，在取出指定的位置 k 的值
 */
fun main() {
    val arr = arrayListOf<Int>(8, 2, 7, 11, 15, 1, 0, 0, 15)
    val k = 3
    quickSort(arr, 0, arr.size - 1,)
    arr.forEach { println(" it ->$it") }
//    println(" result ->$result")

}

var result = 0

fun quickSort(src: ArrayList<Int>, begin: Int, end: Int){

    if (begin < end){
        val key = src[begin]
        var i = begin
        var j = end
        while (i < j){
            while (i < j && src[j] > key){
                j--
            }
            if (i < j){
                swap(src, i, j)
                i++
            }

            while (i < j && src[i] < key){
                i++
            }
            if (i < j ){
                swap(src, i, j)
                j--
            }

        }
        quickSort(src, begin, i - 1);
        quickSort(src, i + 1, end);


    }
}


fun swap(src: ArrayList<Int>, i: Int, j: Int){
    val temp = src[i]
    src[i] = src[j]
    src[j] = temp
}




