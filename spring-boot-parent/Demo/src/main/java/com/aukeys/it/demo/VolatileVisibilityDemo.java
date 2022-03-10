package com.aukeys.it.demo;

public class VolatileVisibilityDemo {

    private static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("waiting data...");
            while (!initFlag){
                System.out.println("111");
            }
            System.out.println("=====>success!");
        }).start();
        //确保线程1已经启动
        Thread.sleep(3000);
        new Thread(()->{
            System.out.println("prepare data...");
            initFlag=true;
            System.out.println("prepare data end..");
        }).start();
    }
}
