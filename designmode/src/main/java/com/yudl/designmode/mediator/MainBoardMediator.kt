package com.yudl.designmode.mediator

import com.yudl.designmode.mediator.colleague.CDDeviceColleague
import com.yudl.designmode.mediator.colleague.Colleague
import com.yudl.designmode.mediator.colleague.CpuColleague

/**
 * @Author: ydl
 * @Description: 主板中介者(具体执行者)
 * @CreateDate: 2021-06-06
 *
 * 使用方法
 * val mainBoard =  MainBoard()
 * val cd = CDDeviceColleague()
 * mainBoard.setCDDeviceColleague(cd)
 * mainBoard.setCpuColleague(CpuColleague())
 * cd.load() 开始加载
 */
class MainBoardMediator :Mediator(){
    //光驱设备
    private var cdDeviceColleague: CDDeviceColleague?= null
    private var cpuColleague:CpuColleague?= null
    override fun changed(colleague : Colleague) {
        when(colleague){
            is CDDeviceColleague->{
                handleCDData()
            }
            is CpuColleague->{
                handCPuData()
            }
        }
    }

    fun setCpuColleague(cpuColleague:CpuColleague){
        this.cpuColleague = cpuColleague
    }

    fun setCDDeviceColleague(cdDeviceColleague: CDDeviceColleague){
        this.cdDeviceColleague = cdDeviceColleague
    }

    private fun handCPuData() {
        val readCPuData = cpuColleague?.readCPuData()!!

    }

    private fun handleCDData() {
        val readData = cdDeviceColleague?.read()!!
        cpuColleague?.load(readData)
    }


}