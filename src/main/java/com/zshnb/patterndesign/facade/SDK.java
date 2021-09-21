package com.zshnb.patterndesign.facade;

public class SDK {
    public void send(String phone, String content) {
        check(phone, content);
        connect();
        doSend(phone, content);
        release();
    }

    private void check(String phone, String content) {
        assert (phone != null && !phone.equals(""));
        assert (content != null && !content.equals(""));
    }

    private void connect() {
        System.out.println("connect");
    }

    private void doSend(String phone, String content) {
        System.out.printf("send %s to %s", content, phone);
    }

    private void release() {
        System.out.println("release");
    }
}
