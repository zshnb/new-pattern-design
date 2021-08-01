package com.zshnb.patterndesign.builder.generator;

public class FieldTypeFormatAndValue {
    private String format;
    private Object value;

    public FieldTypeFormatAndValue(String format, Object value) {
        this.format = format;
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public Object getValue() {
        return value;
    }
}
