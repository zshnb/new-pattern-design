package com.zshnb.patterndesign.proxy.jdk;

import com.zshnb.patterndesign.proxy.Aop;
import com.zshnb.patterndesign.proxy.Before;

@Aop(targetClass = UserDao.class)
public class UserDaoAop {
    @Before(methodName = "listNames")
    public void before() {
        System.out.println("before");
    }
}
