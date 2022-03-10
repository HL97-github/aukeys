package com.aukeys.it.demo;

import com.aukeys.it.entity.User;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<User> {
    @Override
    public User call() throws Exception {
        //
        User user=new User();
        user.setName("123");
        System.out.println(user);
        Thread.sleep(6000);
        System.out.println("返回结果");
        return user;
    }
}
