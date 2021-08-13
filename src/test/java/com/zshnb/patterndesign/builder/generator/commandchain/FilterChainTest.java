package com.zshnb.patterndesign.builder.generator.commandchain;

import com.zshnb.patterndesign.commandchain.FilterChain;
import com.zshnb.patterndesign.commandchain.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class FilterChainTest {
    @Test
    public void ipNotPass() {
        Request request = new Request("10.2.1.1", "en", LocalDateTime.now());
        FilterChain filterChain = new FilterChain();
        Assertions.assertThrows(RuntimeException.class, () -> filterChain.handle(request), "IpFilter not pass");
    }

    @Test
    public void localeNotPass() {
        Request request = new Request("192.2.1.1", "en", LocalDateTime.now());
        FilterChain filterChain = new FilterChain();
        Assertions.assertThrows(RuntimeException.class, () -> filterChain.handle(request), "LocaleFilter not pass");
    }

    @Test
    public void allPass() {
        Request request = new Request("192.2.1.1", "cn", LocalDateTime.now());
        FilterChain filterChain = new FilterChain();
        filterChain.handle(request);
    }
}
