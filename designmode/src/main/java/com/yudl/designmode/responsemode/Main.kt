package com.yudl.designmode.responsemode


/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 责任链设计模式
 * 场景分析：使多个请求对象都有机会去处理请求，从而避免了请求的发送者和接收者之间的耦合关系，将这些对象连成一个链，并沿着这条链去请求，直到有对象有处理为止。
 * 使用场景：多个对象可以处理同一个请求，但是具体有那个对象执行则在运行时动态的决定。在请求处理者不明确的情况下，向多个请求中的一个提交一个请求。
 * 源码中常见的就是 事件分发
 */
fun main() {
    val handler1 = Handler1()
    val handler2 = Handler2()
    handler1.successor = handler2
    handler1.handleRequest("Handler1")
}

abstract class Handler{
    var successor:Handler? = null
    abstract fun  handleRequest(condition: String)
}

class Handler1 : Handler() {
    override fun handleRequest(condition: String) {
        if (condition == "Handler1") {
            print("Handler1  handled ->")
            return
        } else {
            successor?.handleRequest(condition)
        }
    }
}
class Handler2 : Handler() {
    override fun handleRequest(condition: String) {
        if (condition == "Handler2") {
            print("Handler2  handled ->")
            return
        } else {
            successor?.handleRequest(condition)
        }
    }
}
