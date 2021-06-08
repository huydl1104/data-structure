package com.yudl.designmode.mediator.colleague

import com.yudl.designmode.mediator.Mediator

/**
 * @Author: ydl
 * @Description: 光驱同事
 * @CreateDate: 2021-06-06
 */
class CDDeviceColleague(private val mediator: Mediator) : Colleague(mediator) {
    private var data :String?= null

    fun read() :String? = data

    fun load(){
        data = "视频数据，音频数据"
        mediator.changed(this)
    }
}
