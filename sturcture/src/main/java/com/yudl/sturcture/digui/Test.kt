package com.yudl.sturcture.digui

/**
 * @author yudongliang
 * create time 2021-05-31
 * describe : 走出迷宫
 */
fun main() {

    val arr = createArray()
    findWay(arr,1,1)
    println("---------------------")
    print(arr)
}

/**
 * 0：代表没有走过
 * 1：代表是墙
 * 2：已经走过
 * 3：此路不通
 * 逻辑：下 右 上 左 的顺序
 */
fun findWay(arr: Array<Array<Int>>, i: Int, j: Int):Boolean {
    if (arr[6][5] == 2){
        return true
    }else{
        if (arr[i][j] == 0){
            arr[i][j] = 2
            return when {
                findWay(arr, i+1, j) -> {
                    true
                }
                findWay(arr, i, j+1) -> {
                    true
                }
                findWay(arr, i-1, j) -> {
                    true
                }
                findWay(arr, i, j-1) -> {
                    true
                }
                else -> {
                    arr[i][j] = 3
                    false
                }
            }
        }else{
            return false
        }
    }
}

fun createArray(): Array<Array<Int>> {
    val row = 8
    val column = 7
    val arr = Array(row) { Array(column) { 0 } }
    for(i in 0 until 8){
        arr[i][0] = 1
        arr[i][6] = 1
    }
    for(i in 0 until 7){
        arr[0][i] = 1
        arr[7][i] = 1
    }
    arr[3][1] = 1
    arr[3][2] = 1
    arr[4][3] = 1
    print(arr)
    return arr
}

private fun print(arr: Array<Array<Int>>) {
    for (i in 0 until 8) {
        for (j in 0 until 7) {
            print(arr[i][j])
            print("\t")
        }
        println()
    }
}

fun test(num:Int){
    if (num > 2){
        test(num-1)
    }else{
        println("num -> $num")
    }
}
