package com.yudl.designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: ydl
 * @Description: 描述
 * @CreateDate: 2021-06-08
 */
public class DynaimcProxyPerson implements InvocationHandler {

    private Object obj ;
    public DynaimcProxyPerson(Object object){
        this.obj = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}
