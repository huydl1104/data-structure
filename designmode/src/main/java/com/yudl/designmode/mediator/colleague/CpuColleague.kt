package com.yudl.designmode.mediator.colleague

import com.yudl.designmode.mediator.Mediator

/**
 * @Author: ydl
 * @Description: cpu同事
 * @CreateDate: 2021-06-06
 */
class CpuColleague(private val mediator: Mediator):Colleague(mediator) {

    private var cpuData: String?= null
    fun readCPuData():String? = cpuData

    fun load(data:String){

        cpuData = data
        mediator.changed(this)
    }
}