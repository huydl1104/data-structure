package com.yudl.designmode.proxymode

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method


/**
 * @Author: ydl
 * @Description: 描述
 * @CreateDate: 2021-06-08
 */
class DynamicProxy(val obj:Any) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        return method?.invoke(obj, args)!!
    }
}