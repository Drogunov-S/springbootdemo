package ru.drogunov.springbootdemo.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class BenchmarkAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> map = new HashMap<>();
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Benchmark.class) || methodIsAnnotationPresent(aClass)) {
            map.putIfAbsent(beanName, aClass);
            System.out.printf(">>> before init %s%n", beanName);
        }
        return bean;
    }
    
    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return Arrays.stream(aClass.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Benchmark.class));
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (map.get(beanName) != null) {
            bean = proxy(bean);
            System.out.printf("<<< after init %s%n", beanName);
        }
        return bean;
    }
    
    private Object proxy(Object bean) {
        MethodInterceptor handler = (obj, method, arg, methodProxy) -> {
            Object result;
            if (bean.getClass().isAnnotationPresent(Benchmark.class)
                    || method.isAnnotationPresent(Benchmark.class)
            ) {
                System.out.printf("====  Method %s started ====%n", method.getName());
                long time = System.nanoTime();
                result = methodProxy.invoke(bean, arg);
                time = System.nanoTime() - time;
                System.out.printf("====  Method %s complete. time = %.3f ms  ====%n%n", method.getName(), time / 1e6);
            } else {
                result = methodProxy.invoke(bean, arg);
            }
            return result;
        };
        Object proxy = Enhancer.create(bean.getClass(), handler);
        System.out.println("<<< here proxy for >>>" + bean.getClass().getSimpleName());
        return proxy;
        
    }
    
}
