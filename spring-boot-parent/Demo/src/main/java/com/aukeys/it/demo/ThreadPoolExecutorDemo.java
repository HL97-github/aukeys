package com.aukeys.it.demo;

import com.aukeys.it.entity.User;

import java.util.concurrent.FutureTask;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<User> futuretask = new FutureTask<User>(new MyCallable());
        Thread t = new Thread(futuretask);
        t.start();
        System.out.println(futuretask.isDone());
        Thread.sleep(3000);
        System.out.println(futuretask.cancel(true));
        System.out.println(futuretask.isCancelled());
        User user = (User) futuretask.get();
        System.out.println(user);
    }

    private static void method(String s) {
        switch (s) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }
}
