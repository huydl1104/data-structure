package com.yudl.designmode.iteratormode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 迭代器设计模式
 * 场景分析
 *      提供一种方法顺序的访问一个容器对象的各个 元素，而又不需要暴漏改对象的内部表示。
 * 使用场景 ：遍历一个容器对象的数据时
 */
fun main() {
    val mainFunction = MainFunctionImpl<String>()
    mainFunction.add("aaa")
    mainFunction.add("bbb")
    mainFunction.add("ccc")
    val iterator = mainFunction.iterator()
    while (iterator.hasNext()){
        val next = iterator.next()
        println(next)
    }
}

interface Iterator<T>{
    fun hasNext() :Boolean
    fun next(): T?
}

interface MainFunction<T>{
    fun add(t:T)
    fun remove(t:T)
    fun iterator() :Iterator<T>
}

class MainFunctionImpl<T> : MainFunction<T>{
    private val list = ArrayList<T>()
    override fun add(t: T) {
        list.add(t)
    }

    override fun remove(t: T) {
        list.remove(t)
    }

    override fun iterator(): Iterator<T> {
        return IteratorImpl(list)
    }

}

class IteratorImpl<T>(var list: ArrayList<T>) : Iterator<T>{
    var index = 0
    override fun hasNext(): Boolean {
        return index != list.size
    }

    override fun next(): T? {
        if (hasNext()){
            return list[index++]
        }
        return null
    }

}

