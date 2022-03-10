package com.aukeys.it.service.impl;

import com.aukeys.it.service.CallBack;

public class AnswerServiceImpl {
    public void answer(CallBack callBack) {
        System.out.println("在忙其他事...");
        try {
            Thread.sleep(5000);
            System.out.println("忙完其他事，开始计算...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("计算出答案为：2");
        // 回调通过参数将结果返回给真正调用者
        callBack.callback("2");
    }
}
