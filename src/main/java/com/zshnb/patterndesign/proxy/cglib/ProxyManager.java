package com.zshnb.patterndesign.proxy.cglib;

import com.zshnb.patterndesign.proxy.Aop;
import com.zshnb.patterndesign.proxy.Before;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProxyManager implements MethodInterceptor {
    private Class<?> aopClass;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        List<Method> beforeMethods = Arrays.stream(aopClass.getMethods()).filter(it -> it.isAnnotationPresent(Before.class)
            && it.getAnnotation(Before.class).methodName().equals(method.getName())).collect(Collectors.toList());
        beforeMethods.forEach(it -> {
            try {
                it.invoke(aopClass.getConstructor().newInstance(), args);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
            }
        });
        return proxy.invokeSuper(obj, args);
    }

    public Object getProxy(Class<?> targetClass) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackages(this.getClass().getPackage().getName())
            .addScanners(new TypeAnnotationsScanner()));
        this.aopClass = reflections.getTypesAnnotatedWith(Aop.class).stream()
            .filter(it -> it.getAnnotation(Aop.class).targetClass().isAssignableFrom(targetClass))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("target class doesn't has aop annotation or error [targetClass] field"));
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
