package com.aukeys.it.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //具体目标对象
        // IUserDao target = new UserDao();
        // //静态代理对象
        // IUserDao proxy = new StaticProxy(target);
        // proxy.save();
        // ChildUserDao childUserDao=new ChildUserDao();
        // childUserDao.save();

        //具体目标对象
        // IUserDao target = new UserDao();
        // System.out.println(target.getClass());
        // //传递目标对象引用，生产代理对象
        // IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // System.out.println(proxy.getClass());
        // proxy.save();

        //具体目标对象
        IUserDao target = new UserDao();
        System.out.println(target.getClass());
        //传递目标对象引用，生产代理对象
        UserDao proxy = (UserDao) new CGProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();

    }
}
