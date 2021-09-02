package com.zshnb.patterndesign.ioc;

@Bean
public class UserDao {
    private int id = 1;
    @Autowired
    private UserConfig userConfig;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }
}
