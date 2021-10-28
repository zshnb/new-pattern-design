package com.zshnb.patterndesign.proxy.cglib;

import java.util.ArrayList;

public class UserDao {
    public void listNames() {
        new ArrayList<String>(){{
            add("a");
            add("b");
        }}.forEach(System.out::println);
    }
}
