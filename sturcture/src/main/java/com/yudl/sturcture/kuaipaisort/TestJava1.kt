package com.yudl.sturcture.kuaipaisort

/**
 * @author yudongliang
 * create time 2021-05-20
 * describe :
 */
class TestJava1 {

// ------- base ---------
    var resultData : Int = -1

    fun quickSort(arr: ArrayList<Int>,begin: Int,end: Int){
        if (begin < end){
            var key = arr[begin]
            var left = begin
            var right = end
            while (left < right){
                while (left < right && arr[right] >= key){
                    right --
                }
                if (left < right){
                    swap(arr,left,right)
                    left ++
                }
                while (left < right && arr[left] <= key){
                    left ++
                }
                if (left < right){
                    swap(arr,left,right)
                    right --
                }
            }

            quickSort(arr,begin,left - 1)
            quickSort(arr,left + 1,end)

        }

    }
    private fun swap(arr: ArrayList<Int>, i:Int, j:Int){
        val temp = arr[i]
        arr[i] =arr[j]
        arr[j] = temp
    }

// ------- base ---------

    fun QSort(a: IntArray, left: Int, right: Int) {
        if (left >= right) {
            return
        }
        //三数中值分割法选取枢纽元
        val base = median3(a, left, right)
        var i = left
        var j = right - 1
        while (i < j) {
            while (i < j && base > a[++i]) {
            }
            while (i < j && base < a[--j]) {
            }
            if (i < j) {
                swap(a, i, j)
            }
        }
        swap(a, i, right - 1)
        QSort(a, left, i - 1)
        QSort(a, i + 1, right)
    }

    private fun swap(a: IntArray, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }

    //三数中值分割法
    private fun median3(a: IntArray, i: Int, j: Int): Int {
        //对三个数进行排序
        val m = i + j shr 1
        if (a[m] < a[i]) {
            swap(a, i, m)
        }
        if (a[j] < a[i]) {
            swap(a, i, j)
        }
        if (a[j] < a[m]) {
            swap(a, j, m)
        }
        //将枢纽元放在j - 1;
        swap(a, m, j - 1)
        return a[j - 1]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val testJava = TestJava1()
            val arr = intArrayOf(8, 2, 7, 11, 15, 1, 0, 0, 21, 3, 4)
            testJava.QSort(arr, 0, arr.size - 1)
            for (i in arr) {
                println(" it ->$i")
            }
        }
    }
}