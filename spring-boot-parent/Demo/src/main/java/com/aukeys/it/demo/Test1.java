package com.aukeys.it.demo;

public class Test1 {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            System.out.println("-------");
            synchronized (obj) {
                System.out.println("d进入等待---");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束等待---");
            }
        }, "等待线程").start();

        new Thread(() -> {
            System.out.println("我要唤醒锁了");
            synchronized (obj) {
                obj.notify();
            }
        }, "唤醒").start();
    }
}

