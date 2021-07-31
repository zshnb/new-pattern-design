package com.zshnb.patterndesign.builder.generator;

import com.zshnb.patterndesign.builder.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
    @Test
    public void generate() {
        Generator generator = new Generator();
        generator.generate();
        Student student = Student.newBuilder()
            .setId(1)
            .setName("student").build();
        Assertions.assertEquals(1, student.getId());
        Assertions.assertEquals("student", student.getName());
    }
}
