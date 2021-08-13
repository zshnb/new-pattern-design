package com.zshnb.patterndesign.commandchain;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.PriorityQueue;
import java.util.Set;

public class FilterChain {
    private PriorityQueue<FilterDefinition> filterDefinitions = new PriorityQueue<>((o1, o2) -> o2.getPriority() - o1.getPriority());

    public FilterChain() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackages(this.getClass().getPackage().getName())
            .addScanners(new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Order.class);
        classes.forEach(it -> {
            Order order = it.getDeclaredAnnotation(Order.class);
            int priority = order.value();
            try {
                filterDefinitions.add(new FilterDefinition((Filter) it.getDeclaredConstructor().newInstance(), priority));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public void handle(Request request) {
        filterDefinitions.forEach(it -> {
            if (!it.getFilter().pass(request)) {
                throw new RuntimeException(it.getFilter().getClass().getSimpleName() + " not pass");
            }
        });
    }
}
