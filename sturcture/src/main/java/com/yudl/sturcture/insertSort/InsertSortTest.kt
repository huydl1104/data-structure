package com.yudl.sturcture.insertSort

/**
 * @author yudongliang
 * create time 2021-06-03
 * describe : 插入排序
 *      插入排序将数组数据分成已排序区间和未排序区间。初始已排序区间只有一个元素，
 *      即数组第一个元素。在未排序区间取出一个元素插入到已排序区间的合适位置，直到未排序区间为空。
 *      插入排序中 分为已排序 和非排序区 ，大量的进行了赋值操作，效率不会很高不过是一个很好的思考方式
 */

fun main() {
    val arr = arrayOf(3, 7, 1, 4, 5, 0,10,35,60,23,44)
    insertSort(arr)
    customPrint(arr)
}

/**
 * 从 第二个数开始往前遍历 寻找 左侧的值 > 右侧的值 交换
 */
private fun insertSort(arr: Array<Int>){
    for (i in 1 until arr.size){
        val tempValue = arr[i]
        var j :Int = i
        while (j > 0 && arr[j-1] > tempValue){
            arr[j] = arr[j-1]
            j--
        }
        arr[j] = tempValue
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
fun sort2(ins: IntArray): IntArray? {
    for (i in 1 until ins.size) {
        val temp = ins[i] //保存每次需要插入的那个数
        var j: Int
        j = i
        while (j > 0 && ins[j - 1] > temp) {
            //这个较上面有一定的优化
            ins[j] = ins[j - 1] //吧大于需要插入的数往后移动。最后不大于temp的数就空出来j
            j--
        }
        ins[j] = temp //将需要插入的数放入这个位置
    }
    return ins
}*/
