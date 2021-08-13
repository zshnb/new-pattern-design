package com.zshnb.patterndesign.commandchain;

@Order(2)
public class LocaleFilter implements Filter {
    @Override
    public boolean pass(Request request) {
        return request.getLocale().equals("cn");
    }
}
