package com.yudl.designmode.strategymode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 策略设计模式
 * 场景分析：策略模式定义了一系列的算法，将每个算法封装起来，可以相互之间可以替换，策略模式让算法独立于其他的客户存在
 * 使用场景：针对统一类型的不同处理，仅仅行为有所区别。出现同一个抽象类有多个子类，而且需要使用if-else 来选择具体的子类时。
 * 源码：动画中的插值器
 */
fun main() {
    val manager = Manager()
    val bus = Bus()
    manager.setCalculator(bus)
    val calculator = manager.calculator(10)
    println(calculator)
}

interface Calculate{
    fun calculator(num :Int): Int
}

class Bus :Calculate{

    override fun calculator(num: Int): Int {
        return 1 + num
    }

}


class Subway :Calculate{

    override fun calculator(num: Int): Int {
        return 10 + num
    }

}

private class Manager{
    var calculate :Calculate? = null
    fun setCalculator(calculate: Calculate){
        this.calculate = calculate
    }
    fun calculator(num: Int):Int{
       return calculate?.calculator(num)!!
    }
}