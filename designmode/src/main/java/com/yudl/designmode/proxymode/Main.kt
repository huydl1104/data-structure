package com.yudl.designmode.proxymode

import java.lang.reflect.Proxy

/**
 * @Author: ydl
 * @Description: 测试代理模式的代码
 * @CreateDate: 2021-06-08
 */
fun main() {
    val spokenPerson = SpokenPerson()
//    val proxyStatic = ProxyPerson(spokenPerson)
//    proxyStatic.submit()
//    proxyStatic.burden()
//    proxyStatic.defend()
//    proxyStatic.finish()

//    val proxy = DynamicProxy(spokenPerson)
    val proxy = DynaimcProxyPerson(spokenPerson)
    val clazzLoader = spokenPerson.javaClass.classLoader
    val lawSuit = Proxy.newProxyInstance(clazzLoader, arrayOf(ILawSuit::class.java),proxy) as ILawSuit
    lawSuit.submit()
    lawSuit.burden()
    lawSuit.defend()
    lawSuit.finish()
}
