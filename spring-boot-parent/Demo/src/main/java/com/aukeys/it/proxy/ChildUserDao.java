package com.aukeys.it.proxy;

public class ChildUserDao extends UserDao{
    @Override
    public void save() {
        System.out.println("额外操作1");
        super.save();
        System.out.println("额外操作2");
    }
}
