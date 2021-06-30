package com.yudl.designmode.proxymode

/**
 * @Author: ydl
 * @Description: 代理人 
 * @CreateDate: 2021-06-08
 */
class ProxyPerson(private val lawSuit: ILawSuit) :ILawSuit {


    override fun submit() {
        lawSuit.submit()
    }

    override fun burden() {
        lawSuit.burden()
    }

    override fun defend() {
        lawSuit.defend()
    }

    override fun finish() {
        lawSuit.finish()
    }
}