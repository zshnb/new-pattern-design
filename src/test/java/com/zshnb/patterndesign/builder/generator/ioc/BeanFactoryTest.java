package com.zshnb.patterndesign.builder.generator.ioc;

import com.zshnb.patterndesign.ioc.BeanFactory;
import com.zshnb.patterndesign.ioc.BeanNotExist;
import com.zshnb.patterndesign.ioc.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanFactoryTest {
    @Test
    public void getBeanSuccessful() {
        BeanFactory beanFactory = new BeanFactory();
        Assertions.assertEquals(1, beanFactory.getClassNameWithClass().size());
        Assertions.assertEquals(0, beanFactory.getClassNameWithObject().size());
        UserDao userDao = (UserDao) beanFactory.getBean(UserDao.class);
        Assertions.assertEquals(1, userDao.getId());
        Assertions.assertEquals(1, beanFactory.getClassNameWithObject().size());
    }

    @Test
    public void failedWhenClassNotFound() {
        BeanFactory beanFactory = new BeanFactory();
        Assertions.assertThrows(RuntimeException.class, () -> beanFactory.getBean(BeanNotExist.class), "ClassNotFound");
    }
}
