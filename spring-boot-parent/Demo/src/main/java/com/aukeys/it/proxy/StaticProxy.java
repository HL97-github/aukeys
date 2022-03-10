package com.aukeys.it.proxy;

//静态代理对象
public class StaticProxy implements IUserDao  {
    /**
     * 持有目标类的对象，实现间接调用
     */
    private IUserDao iUserDao;

    public StaticProxy(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    public void save() {
        System.out.println("额外操作1");
        if (iUserDao != null) {
            iUserDao.save(); // 实际调用目标功能
        }
        System.out.println("额外操作2");
    }
}
