package com.zshnb.patterndesign.builder.generator.decorator;

import com.zshnb.patterndesign.decorator.ArrayListUti;
import com.zshnb.patterndesign.decorator.PositiveNumberArrayListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositiveNumberArrayListUtilTest {
    @Test
    public void addAndGet() {
        PositiveNumberArrayListUtil<Integer> positiveNumberArrayListUtil = new PositiveNumberArrayListUtil<>(new ArrayListUti<>());
        Assertions.assertThrows(AssertionError.class, () -> positiveNumberArrayListUtil.add(-1));
        positiveNumberArrayListUtil.add(1);
        positiveNumberArrayListUtil.add(2);
        Assertions.assertEquals(2, positiveNumberArrayListUtil.size());
    }
}
