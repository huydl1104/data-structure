package com.yudl.designmode.bridgemode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 桥接设计模式
 * 定义：将抽象部分和实现部分进行分离，可以独立的进行变化。建立关联的联系，两部分都可以进行独立的变化
 *
 * 过程：
 *      抽象接口 ，子类实现  <-->   抽象的桥梁   <--->    子类实现桥梁
 *      使两个业务模块解耦
 */
fun main() {
    val operatorImplA = OperatorImplA()
    val consumerImpl = ConsumerImpl(operatorImplA)
    consumerImpl.operator()

}

interface Operator {
    fun operator()
}

class OperatorImplA : Operator{
    override fun operator() {
        println("OperatorImplA  exe ")
    }
}

class OperatorImplB : Operator{
    override fun operator() {
        println("OperatorImplB  exe ")
    }
}

abstract class Consumer(private val operator: Operator){

    fun operator(){
        preFunction()
        operator.operator()
        lastFunction()
    }

    abstract fun preFunction()

    abstract fun lastFunction()
}

class ConsumerImpl(operator: Operator) : Consumer(operator){

    override fun preFunction() {
        println("ConsumerImpl   preFunction ")
    }

    override fun lastFunction() {
        println("ConsumerImpl   lastFunction ")
    }

}






