package com.zshnb.patterndesign.builder.generator.proxy;

import com.zshnb.patterndesign.proxy.cglib.ProxyManager;
import com.zshnb.patterndesign.proxy.cglib.UserDao;
import org.junit.jupiter.api.Test;

public class CglibProxy {
    @Test
    public void aop() {
        ProxyManager proxyManager = new ProxyManager();
        UserDao userDao = (UserDao) proxyManager.getProxy(UserDao.class);
        userDao.listNames();
    }
}
