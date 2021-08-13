package com.zshnb.patterndesign.ioc;

@Bean
public class UserDao {
    private int id = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
