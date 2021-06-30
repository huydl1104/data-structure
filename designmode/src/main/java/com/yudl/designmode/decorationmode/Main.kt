package com.yudl.designmode.decorationmode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 装饰者设计模式
 * 场景分析：动态的给一个对象增加一些额外的职责，增加功能来说，装饰模式比生成子类更灵活。
 */
fun main() {
    val component = ComponentImpl()
    val customDecorator = CustomDecorator(component)
    customDecorator.operator()
}

abstract class Component{
    abstract fun operator()
}

class ComponentImpl :Component(){
    override fun operator() {
        println("ComponentImpl  operator  ----")
    }

}

open class Decorator(private val component: Component)  :Component(){
    override fun operator() {
        component.operator()
    }

}

class CustomDecorator(component: Component) : Decorator(component){

    override fun operator() {
        preFun()
        super.operator()
        lastFun()
    }

    fun preFun(){
        println("CustomDecorator  preFun  ")
    }

    fun lastFun(){
        println("CustomDecorator  lastFun  ")
    }
}




