package com.aukeys.it.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Object target;// 维护一个目标对象

    //获得目标对象的引用
    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        //newProxyInstance：返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
        //参数一：指定当前目标对象使用类加载器
        //参数二：目标对象实现的接口的类型
        //参数三：事件处理器
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("增强功能1");
                        // 在代理实例上处理方法调用并返回结果。
                        Object invoke = method.invoke(target, args);
                        System.out.println("增强功能2");
                        return invoke;
                    }
                });
    }
}
