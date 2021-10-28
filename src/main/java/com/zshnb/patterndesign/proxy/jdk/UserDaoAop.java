package com.zshnb.patterndesign.proxy.jdk;

@Aop(targetClass = UserDao.class)
public class UserDaoAop {
    @Before(methodName = "listNames")
    public void before() {
        System.out.println("before");
    }
}
