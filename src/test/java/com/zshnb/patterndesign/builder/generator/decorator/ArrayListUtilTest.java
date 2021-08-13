package com.zshnb.patterndesign.builder.generator.decorator;

import com.zshnb.patterndesign.decorator.ArrayListUti;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayListUtilTest {
    @Test
    public void addAndGet() {
        ArrayListUti<Integer> arrayListUti = new ArrayListUti<>();
        arrayListUti.add(1);
        arrayListUti.add(2);
        Assertions.assertEquals(2, arrayListUti.size());
        Assertions.assertEquals(1, arrayListUti.get(0));
    }
}
