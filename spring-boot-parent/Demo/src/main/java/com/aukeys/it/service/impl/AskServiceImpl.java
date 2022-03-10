package com.aukeys.it.service.impl;

import com.aukeys.it.service.CallBack;

/**
 * 请求调用的类
 */
public class AskServiceImpl implements CallBack {

    private AnswerServiceImpl answerService;

    //持有answer的引用调用answer的方法
    public AskServiceImpl(AnswerServiceImpl answerService){
        this.answerService=answerService;
    }
    //接收回调结果
    @Override
    public void callback(String s) {
        System.out.println("收到答案："+s);
    }

    public void ask() {
        //向下传递调用者对象引用，以便回调
        answerService.answer(this);
    }

    public void askASyn() {
        System.out.println("创建新线程请教问题");
        new Thread(() -> {
            answerService.answer(this);
            System.out.println("新创建线程执行结束");
        }).start();
        System.out.println("不等待处理结果...");
    }
}
