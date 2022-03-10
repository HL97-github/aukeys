package com.aukeys.it.demo;

import lombok.*;

@Getter
@Setter
@ToString
//双重检验锁提升效率
public class Singleton {

    private String name;

    private Singleton() {
    }

    //volatile的一个语义是禁止指令重排序优化
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        //提高效率，不等于null时不用获取锁
        if (instance == null) {
            //多线程可以进入这中间，所以加第二个if
            synchronized (Singleton.class) {
                if (instance == null) {
                    //2保证返回instance一定在创建对象后，因为instance=new Singleton()不是原子性语句，所以加入
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
