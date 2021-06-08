package com.yudl.sturcture.calculator

import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

/**
 * @author yudongliang
 * create time 2021-05-31
 * describe : 给出一个字符串实现计算器的功能
 */
fun main() {
    val str = "(7+(14*7)-1) + 7 -9 + 12 *5"
    val expressionStr = replaceCustomAllBlank(str)
    val list = toCustomList(expressionStr)
    println("中缀的集合:$list")
    val parseSuffixList = parseSuffixList(list)
    println("后缀的集合:$parseSuffixList")
    val calculatorResult = calculatorResult(parseSuffixList)
    println("最后的结果:$calculatorResult")

}

fun calculatorResult(list: ArrayList<String>):Double {
    if (list.isEmpty()){
        return 0.0
    }
    val stack = Stack<String>()
    for (str in list){
        if (isNumber(str)){
            //数字直接push到栈中
            stack.push(str)
        }else{
            stack.push(calculator(stack.pop(), stack.pop(), str))
        }
    }
    return stack.pop().toDouble()
}

fun calculator(str1: String, str2: String, operator: String): String {
    var result = ""
    when(operator){
        "+" -> { result = (str2.toDouble() + str1.toDouble()).toString() }
        "-" -> { result = (str2.toDouble() - str1.toDouble()).toString() }
        "*" -> { result = (str2.toDouble() * str1.toDouble()).toString() }
        "/" -> { result = (str2.toDouble() / str1.toDouble()).toString() }
    }
    return result
}

fun operatorPriority(str: String): Int {
    return if (str == "*" || str == "/"){
         2
    }else if (str == "+" || str == "-"){
         1
    }else{
         -1
    }
}

/**
 * 将中缀表达式转换为后缀表达式
 */
fun parseSuffixList(list: List<String>): ArrayList<String> {
    if (list.isEmpty()){
        throw RuntimeException("数据集合为空")
    }
    //栈结构去储存 运算符
    val stack = Stack<String>()
    //使用集合去存放数字
    val resultList = ArrayList<String>()
    for (str in list){
        if (isNumber(str)){
            resultList.add(str)
        }else{
            when (str) {
                "(" -> {
                    //左括号直接入栈
                    stack.push(str)
                }
                ")" -> {
                    //从栈中找到 左括号
                    while (!stack.peek().equals("(")) {
                        resultList.add(stack.pop())
                    }
                    //去除左括号
                    stack.pop()
                }
                else -> {
                    //这种情况 +—*/
                    //当前str的符号优先级 <=  栈顶的符号优先级
                    while (stack.size != 0 && operatorPriority(str) <= operatorPriority(stack.peek())){
                        resultList.add(stack.pop())
                    }
                    //当stack 大小为0时，直接push
                    stack.push(str)
                }
            }
        }
    }
    //将栈中的数据 赋值给 list
    while (stack.size != 0){
        resultList.add(stack.pop())
    }
    return resultList
}

/**
 * 取出所有的空白符
 */
fun replaceCustomAllBlank(string: String):String{
    return Pattern.compile("\\s+").matcher(string).replaceAll("")
}

/**
 * 判断是否是数字的正则表达式,把点，百分号也算成数字
 */
private fun isNumber(str: String):Boolean{
    val pattern = Pattern.compile("^?[.\\d]*?[%\\d]*$")
    val isNum = pattern.matcher(str)
    return isNum.matches()
}

fun toCustomList(expression: String):List<String>{

    if (expression.trim() == ""){
        throw RuntimeException("数据为空")
    }
    if (!isNumber(expression[0].toString()) && expression[0].toString() != "("){
        throw java.lang.RuntimeException("数据格式不正确")
    }
    val list = ArrayList<String>()
    var index = 0
    //通过这个去拼接字符串 连着是两个数字的情况
    var builder = StringBuilder()
    //分为两个部分 数字和 非数字之分
    do {
        if (!isNumber(expression[index].toString())){
            list.add(expression[index].toString())
            index++
        }else{
            while (index < expression.length && isNumber(expression[index].toString())){
                builder.append(expression[index].toString())
                if (index >= expression.length){
                    break
                }
                index++
            }
            list.add(builder.toString())
            builder = StringBuilder()
        }

    } while (index < expression.length)

    return list
}
