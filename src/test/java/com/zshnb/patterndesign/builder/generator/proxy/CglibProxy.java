package com.zshnb.patterndesign.builder.generator.proxy;

import com.zshnb.patterndesign.proxy.cglib.ProxyManager;
import com.zshnb.patterndesign.proxy.cglib.UserDao;
import com.zshnb.patterndesign.proxy.cglib.UserDaoProxy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class CglibProxy {
    @Test
    public void aop() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProxyManager proxyManager = new ProxyManager();
        UserDao userDao = (UserDao) proxyManager.getProxy(UserDao.class);
        userDao.listNames();
    }
}
