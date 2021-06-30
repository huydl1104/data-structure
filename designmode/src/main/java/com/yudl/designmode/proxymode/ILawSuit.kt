package com.yudl.designmode.proxymode

/**
 * @Author: ydl
 * @Description: 具体讼诉人都有那些诉求
 * @CreateDate: 2021-06-08
 */
interface ILawSuit {
    //提交申请
    fun submit()
    //进行举证
    fun burden()
    //开始辩护
    fun defend()
    //诉讼完成
    fun finish()
}