package com.zshnb.patterndesign.ioc;

@Bean
public class UserConfig {
    private String name;

    public UserConfig() {
        name = "UserConfig";
    }
}
