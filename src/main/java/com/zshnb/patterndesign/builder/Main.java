package com.zshnb.patterndesign.builder;

public class Main {
    public static void main(String[] args) {
        User user = User.newBuilder()
            .setId(1)
            .setName("name")
            .setAddress("address")
            .setSex("sex").build();
        System.out.println(user);

        user = user.toBuilder()
            .setSex("sex2").build();
        System.out.println(user);

        user = user.toBuilder()
            .clearAddress().build();
        System.out.println(user);

        GetUserResponse response = GetUserResponse.newBuilder()
            .addUser(user)
            .setTotal(5).build();

        System.out.println(response);
    }
}
