package com.yudl.sturcture.xishuArray

/**
 * @author yudongliang
 * create time 2021-05-26
 * describe : 稀疏数组
 * 思路：
 * 原数组 -> 稀疏数组
 * 遍历原始的二维数组，得到有效数据的个数 sum
 * 根据sum来创建 稀疏数组的行  sparseArr int[sum+1][3]
 * 将二维数组的有效数据存入 稀疏数组中。
 * 稀疏数组 -> 原数组
 * 先读取 稀疏数组的第一行，根据第一行的原始数据创建原始的二维数组，
 * 取出稀疏数组中的有效数据， 存如 给 原始的二维数组中。
 */
object xishuArray1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val row = 11
        val column = 11
        //二维数组
        val arr = Array(row) { IntArray(column) }
        arr[1][1] = 1
        arr[4][4] = 2
        var count = 0
        //遍历 得到 有效的数字
        for (ints in arr) {
            for (anInt in ints) {
                if (anInt != 0) {
                    count++
                }
            }
        }
        System.out.printf("count %d \n", count)
        val sparseArr = Array(count + 1) { IntArray(3) }
        sparseArr[0][0] = row
        sparseArr[0][1] = column
        sparseArr[0][2] = count
        var index = 0
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (arr[i][j] != 0) {
                    index++
                    sparseArr[index][0] = i
                    sparseArr[index][1] = j
                    sparseArr[index][2] = arr[i][j]
                    System.out.printf("有效的数字 %d \t", arr[i][j])
                }
            }
        }
        println()
        for (ints in sparseArr) {
            for (anInt in ints) {
                System.out.printf("结果 %d", anInt)
            }
            println()
        }
        val srcArr = Array(sparseArr[0][0]) { IntArray(sparseArr[0][1]) }
        srcArr[sparseArr[1][0]][sparseArr[1][1]] = sparseArr[1][2]
        srcArr[sparseArr[2][0]][sparseArr[2][1]] = sparseArr[2][2]
        println("-------------------")
        for (ints in srcArr) {
            for (anInt in ints) {
                System.out.printf("src %d\t", anInt)
            }
            println()
        }
    }
}