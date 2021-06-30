package com.yudl.designmode.appearancemode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 外观设计模式
 * 场景分析：在各种的SDk中，要求子系统的外部与内部的通信必须通过统一的对象，更利于维护和使用
 * 过程
 *      内部封装了细节，类似于 封装对象
 */

fun main() {
    val manager = Manager()
    manager.together()
}

class OperatorA {
    fun operator(){
        println("OperatorA  operator ---  ")
    }
}

class OperatorB {
    fun operator(){
        println("OperatorB  operator ---  ")
    }
}

class Manager{
    private val operatorA = OperatorA()
    private val operatorB = OperatorB()

    fun together(){
        extensionFun()
        operatorA.operator()
        operatorB.operator()
    }

    private fun extensionFun(){
        println("Manager  extensionFun ---  ")
    }
}


