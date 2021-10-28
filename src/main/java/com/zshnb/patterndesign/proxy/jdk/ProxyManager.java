package com.zshnb.patterndesign.proxy.jdk;

import com.zshnb.patterndesign.ioc.Bean;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProxyManager {
    public Object getProxy(Object target) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackages(this.getClass().getPackage().getName())
            .addScanners(new TypeAnnotationsScanner()));
        Class<?> clazz = reflections.getTypesAnnotatedWith(Aop.class).stream()
            .filter(it -> it.getAnnotation(Aop.class).targetClass().isAssignableFrom(target.getClass()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("target class doesn't has aop annotation or error [targetClass] field"));
        InvocationHandler handler = (proxy, method, args) -> {
            List<Method> beforeMethods = Arrays.stream(clazz.getMethods()).filter(it -> it.isAnnotationPresent(Before.class)
                    && it.getAnnotation(Before.class).methodName().equals(method.getName()))
                .collect(Collectors.toList());
            if (!beforeMethods.isEmpty()) {
                beforeMethods.forEach(it -> {
                    try {
                        it.invoke(clazz.getConstructor().newInstance());
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                        e.printStackTrace();
                    }
                });
            }
            method.invoke(target, args);
            return null;
        };
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
