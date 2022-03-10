package com.aukeys.it.demo;


public class Demo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                //同时休眠3秒保证三个线程尽可能同时启动
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1执行");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                t1.join();//等待t1执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2执行");
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                t2.join();//等待t2执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3执行");
        });
        t1.start();
        t2.start();
        t3.start();
        System.out.println("main线程执行完成！");
    }
}
