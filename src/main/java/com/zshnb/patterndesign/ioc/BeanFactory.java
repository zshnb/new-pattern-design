package com.zshnb.patterndesign.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private Map<String, Class<?>> classNameWithClass = new HashMap<>();
    private Map<Class<?>, Object> classNameWithObject = new HashMap<>();

    public BeanFactory() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackages(this.getClass().getPackage().getName())
            .addScanners(new TypeAnnotationsScanner()));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Bean.class);
        classes.forEach(it -> classNameWithClass.put(it.getName(), it));
    }

    public Object getBean(Class<?> clz) {
        if (!classNameWithClass.containsKey(clz.getName())) {
            throw new RuntimeException("ClassNotFound");
        }
        if (classNameWithObject.containsKey(clz)) {
            return classNameWithObject.get(clz);
        }
        try {
            Object o = clz.getDeclaredConstructor().newInstance();
            classNameWithObject.put(clz, o);
            return o;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("ClassNotFound");
        }
    }

    public Map<Class<?>, Object> getClassNameWithObject() {
        return classNameWithObject;
    }

    public Map<String, Class<?>> getClassNameWithClass() {
        return classNameWithClass;
    }
}
