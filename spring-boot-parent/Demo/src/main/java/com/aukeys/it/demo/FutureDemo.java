package com.aukeys.it.demo;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {

        //实现一个Callable接口
        Callable<User> c = () -> {
            //这里是业务逻辑处理,需要执行的任务代码
            //让当前线程阻塞3秒看下效果
            Thread.sleep(3000);
            return new User("张三");
        };
        System.out.println("123");
        ExecutorService es = Executors.newFixedThreadPool(2);

        // 记得要用submit，执行Callable对象
        Future<User> fn = es.submit(c);
        // 一定要调用这个方法，不然executorService.isTerminated()永远不为true
        es.shutdown();
        // 无限循环等待任务处理完毕  如果已经处理完毕 isDone返回true
        while (!fn.isDone()) {
            try {
                //处理完毕后返回的结果
                User nt = fn.get();
                System.out.println(nt.name);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class User {
        private String name;

        private User(String name) {
            this.name = name;
        }
    }
}
