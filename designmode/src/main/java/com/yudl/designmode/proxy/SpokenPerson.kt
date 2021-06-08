package com.yudl.designmode.proxy

/**
 * @Author: ydl
 * @Description: 具体的诉讼人
 * @CreateDate: 2021-06-08
 */
class SpokenPerson : ILawSuit {
    override fun submit() {
        println("SpokenPerson submit ")
    }

    override fun burden() {
        println("SpokenPerson burden ")
    }

    override fun defend() {
        println("SpokenPerson defend ")
    }

    override fun finish() {
        println("SpokenPerson finish ")
    }
}