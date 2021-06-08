package com.yudl.designmode.mediator.colleague

import com.yudl.designmode.mediator.Mediator

/**
 * @Author: ydl
 * @Description: 抽象同事 ，每个同事都应该知道中介者
 * @CreateDate: 2021-06-06
 */
abstract class Colleague(mediator: Mediator) {
    init {
        println("Colleague init ")
    }
}
