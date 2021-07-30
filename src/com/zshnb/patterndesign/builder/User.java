package com.zshnb.patterndesign.builder;

public class User {
    private int id;
    private String name;
    private String sex;
    private String address;

    private User() {
        id = 0;
        name = "";
        sex = "";
        address = "";
    }

    public static class Builder {
        private User user;
        private Builder (User user) {
            this.user = user;
        }

        public Builder setId(int id) {
            user.id = id;
            return this;
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setSex(String sex) {
            user.sex = sex;
            return this;
        }

        public Builder setAddress(String address) {
            user.address = address;
            return this;
        }

        public User build() {
            return user;
        }
    }

    public static Builder newBuilder() {
        return new Builder(new User());
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
