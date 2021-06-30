package com.yudl.designmode.visitormode


/**
 * @author yudongliang
 * create time 2021-06-30
 * describe : 访问者设计模式
 * 场景分析：用于某些数据结构中的各种元素的操作，在不改变数据结构的前提下，定义作用于这些元素的新操作。
 *
 */
fun main() {
    val engineer1 = Engineer("工程师1")
    val engineer2 = Engineer("工程师2")
    val engineer3 = Engineer("工程师3")
    val engineer4 = Engineer("工程师4")

    val manager = Manager("经理")
    val boss = Boss()
    val list = arrayListOf<Staff>()
    list.add(engineer1)
    list.add(engineer2)
    list.add(engineer3)
    list.add(engineer4)
    list.add(manager)
    for (staff in list){
        staff.accept(boss)
    }
}



interface Visitor{
    fun visit(engineer: Engineer)
    fun visit(manager: Manager)
}

abstract class Staff(var name:String){
    private var kpi :Int = 0
    init {
        kpi = java.util.Random().nextInt(10)
    }
    abstract fun accept(visitor: Visitor)

}

class Engineer(name: String) : Staff(name){

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    fun getKpi():Int = java.util.Random().nextInt(10 * 100)

}

class Manager(name: String) : Staff(name){
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
    fun getKpi():Int = java.util.Random().nextInt(10 * 1000)
}

class Boss :Visitor{

    override fun visit(engineer: Engineer) {
        println("${engineer.name}  ,${engineer.getKpi()}")
    }

    override fun visit(manager: Manager) {
        println("${manager.name}  ,${manager.getKpi()}")
    }


}


