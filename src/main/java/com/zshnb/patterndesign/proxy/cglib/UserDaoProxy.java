package com.zshnb.patterndesign.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserDaoProxy implements MethodInterceptor {
    private Object target;
    public UserDaoProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        methodProxy.invoke(target, objects);
        System.out.println("after");
        return o;
    }
}
