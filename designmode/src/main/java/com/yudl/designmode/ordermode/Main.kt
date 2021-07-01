package com.yudl.designmode.ordermode

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 命令者设计模式
 * 场景分析：将一个请求封装为一个对象，从而使用户使用不同的请求把客户端参数化，对请求排队或者亲环球日志，以及支持可撤销的操作。
 * 使用场景：需要抽象出待执行的动作，然后参数的形式提供出来，厄里斯与过程设计中的回调机制，而命令模式正式回调机制的替代品。
 */
fun main() {
    val gameFunction = GameFunction()
    val left =  LeftImpl(gameFunction)
    val right =  RightImpl(gameFunction)
    val up =  UpImpl(gameFunction)
    val down =  DownImpl(gameFunction)
    val button = Button()
    button.setDownImpl(down).setLeftImpl(left).setRightImpl(right).setUpImpl(up)
    button.toLeft()
    button.toUp()
    button.toRight()
    button.toDown()
}

class GameFunction{
    fun toLeft(){
        println(" to left ... ")
    }
    fun toRight(){
        println(" to right ... ")
    }
    fun toUp(){
        println(" to up ... ")
    }
    fun toDown(){
        println(" to down ... ")
    }
}

interface Commend{
    fun execute()
}

class LeftImpl(var gameFunction: GameFunction) :Commend{
    override fun execute() {
        gameFunction.toLeft()
    }
}
class UpImpl(var gameFunction: GameFunction) :Commend{
    override fun execute() {
        gameFunction.toUp()
    }
}
class RightImpl(var gameFunction: GameFunction) :Commend{
    override fun execute() {
        gameFunction.toRight()
    }
}
class DownImpl(var gameFunction: GameFunction) :Commend{
    override fun execute() {
        gameFunction.toDown()
    }
}

class Button {
    private var left :LeftImpl? =null
    private var up :UpImpl? =null
    private var right :RightImpl? =null
    private var down :DownImpl? =null
    fun setLeftImpl(leftImpl: LeftImpl): Button {
        this.left = leftImpl
        return this
    }
    fun setUpImpl(upImpl: UpImpl): Button {
        this.up = upImpl
        return this
    }
    fun setRightImpl(rightImpl: RightImpl): Button {
        this.right = rightImpl
        return this
    }
    fun setDownImpl(downImpl: DownImpl): Button {
        this.down = downImpl
        return this
    }

    fun toLeft(){
        left?.execute()
    }
    fun toRight(){
        right?.execute()
    }
    fun toUp(){
        up?.execute()
    }
    fun toDown(){
        down?.execute()
    }
}




