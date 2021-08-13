package com.zshnb.patterndesign.commandchain;

public interface Filter {
    boolean pass(Request request);
}
