package com.aukeys.it.demo;

import com.aukeys.it.service.impl.AnswerServiceImpl;
import com.aukeys.it.service.impl.AskServiceImpl;

public class DemoTest {
    public static void main(String[] args){
        AnswerServiceImpl answer=new AnswerServiceImpl();
        //ask要持有answer的引用，通过构造参数传递
        AskServiceImpl askService=new AskServiceImpl(answer);
        // askService.ask();
        // 新开线程不等结果,异步调用
        askService.askASyn();
    }
}
