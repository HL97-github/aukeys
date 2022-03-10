package com.aukeys.it.demo;

public class SleepDemo {
    public static void main(String[] args) {
        // Thread t1=new Thread(()->{
        //     try {
        //         System.out.println("线程t1开始");
        //         for (int i = 0; i <10000 ; i++) {
        //             System.out.println(1);
        //         }
        //         Thread.sleep(10000);
        //         System.out.println("线程t1结束");
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //         System.out.println("先被中断，再遇到sleep，进入异常");
        //         System.out.println(Thread.currentThread().isInterrupted());
        //     }
        //
        // });
        // t1.start();
        // t1.interrupt();

        Thread t1=new Thread(()->{
            try {
                System.out.println("线程t1开始");
                Thread.sleep(10000);
                System.out.println("线程t1结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("先被中断，再遇到sleep，进入异常");
                System.out.println(Thread.currentThread().isInterrupted());
            }

        });
        t1.start();
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        t1.interrupt();
    }
}
