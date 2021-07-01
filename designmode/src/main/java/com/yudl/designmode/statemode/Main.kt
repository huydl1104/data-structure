package com.yudl.designmode.statemode

import android.content.Context

/**
 * @author yudongliang
 * create time 2021-07-01
 * describe : 状态设计模式
 * 场景分析：状态模式中的行为 是由状态决定的，不同的状态有不同的行为，状态模式和策略模式结构完全一样，但是目的和本质却完全不同，
 *       状态模式的行为时 平行的，不可替换的。而策略模式的行为是彼此独立的，可替换的，状态模式吧对象的行为包装在不同的状态对象中，
 *       每一个状态都有一个共同的抽象状态基类。状态模式的意图是一个对象在内部的状态发生改变的时候，其行为也随之发生改变。
 * 使用场景：代码中包含大量与对象相关的语句条件，一个操作中包含大量的  if - else 的分支依赖于对象内部的状态。
 */
fun main() {
    StateManager.getInstance().setUserState(LoginState())
    StateManager.getInstance().setUserState(LoginOutState())
}

interface UserState{
    fun forWord(context: Context)
    fun comment(context: Context)
}

class LoginState : UserState{
    override fun forWord(context: Context) {
        println("LoginState  forWord --- ")
    }

    override fun comment(context: Context) {
        println("LoginState  comment --- ")
    }
}

class LoginOutState : UserState{
    override fun forWord(context: Context) {
        println("LoginOutState  forWord --- ")
    }

    override fun comment(context: Context) {
        println("LoginOutState  comment --- ")
    }
}

class StateManager{

    companion object{

        var manager :StateManager?= null
        @JvmStatic
        fun getInstance(): StateManager {
            if (manager == null){
                manager = StateManager()
            }
            return manager!!
        }
    }

    var state :UserState?= null

    fun setUserState(state: UserState){
         this.state = state
    }

    fun forWord(context: Context){
        state?.forWord(context)
    }

    fun comment(context: Context){
        state?.comment(context)
    }
}




