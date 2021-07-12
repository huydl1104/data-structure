package com.example.leetcode.array

/**
 * @author yudongliang
 * create time 2021-07-07
 * describe : 找零钱
 * 题目：柠檬摊上每一杯的柠檬水售价为5元，顾客购买的产品一次一杯，顾客付钱为 5元，10元，20元，必须给每个顾客找零钱
 * 开始手头没有任何零钱，能欧找零 返回 true，否则返回 false

 */

fun main() {
    val array = arrayOf(5,10,5,5,20,10)
    val result = findMoney(array)
    println(result)
}

private fun findMoney(array: Array<Int>):Boolean{
    var five = 0
    var ten = 0
    for (value in array){
        if (value == 5){
            five ++
        }else if (value == 10){
            if (five >= 1){
                five--
                ten++
            }else {
                return false
            }
        }else {
            //使用贪心算法 留更多的 5元
            if (ten >= 1 && five >= 1){
                ten--
                five--
            }else if(five >= 3){
                five -= 3
            }else{
                return false
            }
        }
    }
    return true
}

