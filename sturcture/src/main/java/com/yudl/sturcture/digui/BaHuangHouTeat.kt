package com.yudl.sturcture.digui

import kotlin.math.abs

/**
 * @author yudongliang
 * create time 2021-06-01
 * describe : 八皇后的问题解决方案
 */
//代表总共八个皇后
const val max = 8
//使用一位数组的形式来处理这个问题
val array = IntArray(max)

var count = 0
var judgeCount = 0
fun main() {
    check(0)
    println("count ->$count")
    println("judgeCount ->$judgeCount")
}

/**
 * 开始放置皇后
 */
fun check(n: Int){
    //说明已经到第9个了，不网下走了
    if (n == max){
        print()
        count++
        return
    }
    for (i in 0 until max){
        //开始对皇后的位置进行赋值，使用的是 数组的角标和纵坐标的值相等的特性
        array[n] = i
        if (judge(n)){
            check(n+1)
        }
    }
}


/**
 * 校验 放的皇后是否有效
 */
fun judge(n:Int):Boolean{
    judgeCount++
    for(index in 0 until n){
        //相同的一列直接返回，无效
        //若是在一个斜线上直接返回，无效
        if (array[index] == array[n] || abs(n-index) == abs(array[n] - array[index])){
            return false
        }
    }
    return true
}

fun print(){
    for (i in array.indices) {
        print(array[i])
        print("\t")
    }
    println()
}