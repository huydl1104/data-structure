package com.yudl.designmode.mediator

import com.yudl.designmode.mediator.colleague.Colleague

/**
 * @Author: ydl
 * @Description: 抽象中介者
 * @CreateDate: 2021-06-06
 */
abstract class Mediator {
    //同事对象改变时，由中介者去通知其他的同事对象
    abstract fun changed(c: Colleague)
}