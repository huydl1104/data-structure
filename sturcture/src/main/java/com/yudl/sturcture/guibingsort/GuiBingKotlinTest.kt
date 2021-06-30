package com.yudl.sturcture.guibingsort

/**
 * @author yudongliang
 * create time 2021-05-21
 * describe : 归并将原来的数组， 先进行拆分在进行合并
 */

fun main() {

    val arr = arrayOf (10, 14, 73, 25, 23)

    guiBingSort(arr,0,arr.size - 1)
    customPrint(arr)
}

private fun customPrint(arr: Array<Int>){
    for (value in arr){
        print("$value \t")
    }
    println()
}

//
private fun guiBingSort(arr:Array<Int>, low:Int, high:Int){
    val mid = (low + high) shr 1
    if (low < high){
        guiBingSort(arr,low,mid)
        guiBingSort(arr,mid+1, high)
        merge_(arr,low,mid,high)
    }
}

private fun merge_(arr: Array<Int>, low: Int, mid: Int, high: Int) {
    //创建临时的数组
    val tempArr = Array(high-low+1){0}
    var i = low
    var j = mid + 1
    var k = 0
    //在两个条件都满足时，成对存在比较数据的大小
    while (i<=mid && j<=high){
        if (arr[i] > arr[j]){
            tempArr[k++] = arr[j++]
        }else{
            tempArr[k++] = arr[i++]
        }
    }

    //校验 mid 左侧的数据是否还有剩余，若有将其添加到 tempArr中
    while (i <= mid){
        tempArr[k++] = arr[i++]
    }

    //校验 mid 右侧的数据是否还有剩余，若有将其添加到 tempArr中
    while (j <= high){
        tempArr[k++] = arr[j++]
    }
    //将临时的数组 覆盖 原来的数组中
    for (index in tempArr.indices) {
        arr[low+index] = tempArr[index]
    }
}













