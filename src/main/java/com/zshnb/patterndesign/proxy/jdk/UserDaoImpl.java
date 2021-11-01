package com.zshnb.patterndesign.proxy.jdk;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public void listNames() {
        new ArrayList<String>(){{
            add("a");
            add("b");
        }}.forEach(System.out::println);
    }
}
