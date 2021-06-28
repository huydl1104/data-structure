package com.yudl.sturcture.kmp

/**
 * @Author: ydl
 * @Description: kmp算法
 * @CreateDate: 2021-06-26
 */
fun main() {

    val str1 = "BBC ABCDAB ABCDABCDABDE"
    val str2 = "ABCDABD"
    val kmpNext = kmpNext(str2)
    println(kmpNext.toList())
    val kmpSearch = kmpSearch(str1, str2, kmpNext)
    println(kmpSearch)
}


fun kmpSearch (str1 :String ,str2:String ,array: Array<Int>): Int {
    var i = 0
    var j = 0
    while (i < str1.length){

        while (j > 0 && str1[i] != str2[j]){
            j = array[j-1]
        }

        if (str1[i] == str2[j]) {
            j++
        }
        if (j == str2.length){
            return i - j +1
        }

        i++
    }
    return -1
}
/**
 * 一个字串得到部分匹配值
 */
fun kmpNext(childStr:String):Array<Int>{
    val arr = Array<Int>(childStr.length){0}
    //若是等于 1 部分匹配值就是 0
    arr[0] = 0

    var i = 1
    var j = 0
    while (i < childStr.length){

        while (j > 0 && childStr[i] != childStr[j]){
            j = arr[j-1]
        }

        if (childStr[i] == childStr[j]){
            j++
        }
        arr[i] = j


        i++
    }
    return arr
}
