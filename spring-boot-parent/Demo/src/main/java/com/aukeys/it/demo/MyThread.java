package com.aukeys.it.demo;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                int y = i / 0;
            }
            System.out.println("thread:" + i);
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();//开辟一个新栈空间
        for (int i = 0; i < 100; i++) {
            System.out.println("main:" + i);
        }
    }
}
