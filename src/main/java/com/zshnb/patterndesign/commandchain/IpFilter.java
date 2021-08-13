package com.zshnb.patterndesign.commandchain;

@Order(1)
public class IpFilter implements Filter {
    @Override
    public boolean pass(Request request) {
        return request.getIp().startsWith("192");
    }
}
