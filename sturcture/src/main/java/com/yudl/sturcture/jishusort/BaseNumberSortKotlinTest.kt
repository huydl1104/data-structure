package com.yudl.sturcture.jishusort

/**
 * @author yudongliang
 * create time 2021-06-04
 * describe : 基数排序
 *  思路：使用空间换时间的概念，创建一个二位数组，用于根据 数据的来得到 存放的位置。
 */
object BaseNumberSortKotlinTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val A = arrayOf(73, 22, 93, 43, 55, 14, 28, 65, 39, 81)
        baseNumberSort(A, 100)
        for (num in A) {
            print("$num \t")
        }
    }

    /**
     * 基数排序
     *  思路：从个位开始到最高位，去排列，需要的使用空间换时间
     *  max : 每个数中最高的位
     */
    fun baseNumberSort(srcArr: Array<Int>, max :Int){
        var location = 1 //1: 个位；10 ：十位 100 ：百位
        //需要一个二维数组
        val array = Array(10){IntArray(srcArr.size)}
        //使用一个数组来记录没有 位置有多少个数
        val order = Array(srcArr.size){0}
        //从0开始覆盖原来数组的值
        var k = 0
        while (location < max){ // 1 10
            //将原数组中的数据 根绝角标放在指定的位置，
            //使用order数组根据数据的个位十位百位的角标来来给对应的位置进行 +1的操作
            for (value in srcArr){ // 73, 22, 93, 43, 55, 14, 28, 65, 39, 81
                val num = value / location % 10
                array[num][order[num]] = value
                order[num]++
            }
            //遍历order的数组
            for (i in order.indices){
                //过滤 其中为 0 的数
                if (order[i] != 0){
                    for (j in 0 until order[i]){
                        srcArr[k++] = array[i][j]
                    }
                }
                //遍历完成后对 order中对应的数据 进行释放
                order[i] = 0
            }

            location *= 10
            k = 0
        }
    }




































    /*private fun radixSort(array: IntArray, d: Int) {
        var n = 1 //代表位数对应的数：1,10,100...
        var k = 0 //保存每一位排序后的结果用于下一位的排序输入
        val length = array.size
        val bucket = Array(10) { IntArray(length) } //排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        //order {0,1,1,3,1,2,0,0,1,1}
        val order = IntArray(length) //用于保存每个桶里有多少个数字
        while (n < d) {
            for (num in array)  //将数组array里的每个数字放在相应的桶里
            {
                val digit = num / n % 10 //73
                bucket[digit][order[digit]] = num
                order[digit]++
            }
            for (i in 0 until length)  //将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if (order[i] != 0) //这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (j in 0 until order[i]) {
                        array[k] = bucket[i][j]
                        k++
                    }
                }
                order[i] = 0 //将桶里计数器置0，用于下一次位排序
            }
            n *= 10
            k = 0 //将k置0，用于下一轮保存位排序结果
        }
    }*/
}