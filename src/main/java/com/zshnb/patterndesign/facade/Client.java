package com.zshnb.patterndesign.facade;

public class Client {
    private SDK sdk;

    public Client(SDK sdk) {
        this.sdk = sdk;
    }

    public void send(String phone, String content) {
        sdk.send(phone, content);
    }
}
