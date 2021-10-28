package com.zshnb.patterndesign.builder.generator.proxy;

import com.zshnb.patterndesign.proxy.jdk.*;
import org.junit.jupiter.api.Test;

public class JdkProxy {
    @Test
    public void aop() {
        UserDao userDao = new UserDaoImpl();
        UserDao proxyDao = (UserDao) new ProxyManager().getProxy(userDao);
        proxyDao.listNames();
    }
}
