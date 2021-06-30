package com.yudl.designmode.adaptermode

/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 适配器设计模式
 */
fun main() {
    val  manager = Manager()
    manager.setAdapter(object : MyAdapter(){
        override fun getName(): String {
            return "xiao"
        }

        override fun getLevel(): String {
           return "优秀"
        }
    })
    val info = manager.getInfo()
    println(info)
}

abstract class MyAdapter{
    abstract fun getName():String
    abstract fun getLevel():String

}

class Manager{
    private var adapter :MyAdapter?= null

    fun setAdapter(adapter: MyAdapter){
        this.adapter = adapter
    }

    fun getInfo(): String {
        return adapter?.getName().plus("---").plus(adapter?.getLevel())
    }
}


