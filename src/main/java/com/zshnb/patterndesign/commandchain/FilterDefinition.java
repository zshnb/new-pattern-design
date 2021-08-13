package com.zshnb.patterndesign.commandchain;

public class FilterDefinition {
    private Filter filter;
    private int priority;

    public FilterDefinition(Filter filter, int priority) {
        this.filter = filter;
        this.priority = priority;
    }

    public Filter getFilter() {
        return filter;
    }

    public Integer getPriority() {
        return priority;
    }
}
