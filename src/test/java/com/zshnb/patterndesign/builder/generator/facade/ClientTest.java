package com.zshnb.patterndesign.builder.generator.facade;

import com.zshnb.patterndesign.facade.Client;
import com.zshnb.patterndesign.facade.SDK;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    public void sendSuccessful() {
        Client client = new Client(new SDK());
        client.send("123", "con");
    }

    @Test
    public void failed() {
        Client client = new Client(new SDK());
        Assertions.assertThrows(AssertionError.class, () -> client.send("", ""));
    }
}
