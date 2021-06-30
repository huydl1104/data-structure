package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 求数组的中心下标
 *  sum + value + sum = total
 *
 */
fun main() {
//    val array = intArrayOf(5, 6, 9, 11, 14, 10, 10, 11)
    val array = intArrayOf(0,0,0,0,11,12,14,23)
    val total = sum(array)
    //sdk >= 24 可以使用 流的方式 求和
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        Arrays.stream(array).sum()
//    }
    var sum = 0
    var index = 0
    for (i in array.indices){
        val temp = array[i]
        if ((total- temp)/2 == sum){
            index = i
        }
        sum += temp
    }

    println(index)


}

fun sum(array: IntArray):Int{
    var sum = 0
    for (i in array){
        sum += i
    }
    return sum
}
