package com.yudl.designmode.memorymode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 备忘录设计模式
 * 场景分析：在不破坏对象封闭的前提下，捕获一个对象的内部状态，柄在改对象之外保存整个状态。以后回复对象就可以将改对象回复到原先保存的状态。
 * 使用场景：需要保存一个对象在某一时刻的状态或者部分状态。若是通过接口让其他的对象的得到这些状态，将会暴露口对象的实现细节并破坏对象的封装性
 *      一个对象不想被外界直接访问其内部的状态，可以通过中间对象间接的访问内部的状态
 */
fun main() {

}
data class Info(var name:String,var address:String)

class Memory(var name:String,var address:String){

    var info = Info(name,address)

}

