package com.aukeys.it.testone;

import ch.qos.logback.core.util.FileUtil;
import com.aukeys.it.SpringbootApplication;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadPoolTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setKeepAliveSeconds(6000);
        executor.initialize();
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // List<String> list=new ArrayList<>();
        // list.add("1");
        // list.add("2");
        // list.add("3");
        // list.add("4");
        // list.add("5");
        // AtomicInteger i=new AtomicInteger(0);
        // for (String s : list) {
        //     executor.execute(new Runnable() {
        //         @Override
        //         public void run() {
        //             // Thread.currentThread().setName("Thread-"+i);
        //             log.info("{}开始执行任务",Thread.currentThread().getName());
        //             i.incrementAndGet();
        //         }
        //     });
        // }
        executor.execute(() -> {
            try {
                Thread.sleep(60000);
                System.out.println("子线程继续执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        int i = 0;
        // do {
        //     try {
        //         Thread.sleep(3000);
        //         log.info("等待三秒");
        //         i++;
        //         if(i==2){
        //             log.info("等待六秒完成，强行结束任务");
        //             executor.shutdown();
        //             return;
        //         }
        //     } catch (Exception e) {
        //         log.error(e.getMessage(), e);
        //     }
        // } while (executor.getThreadPoolExecutor().getTaskCount() != executor.getThreadPoolExecutor().getCompletedTaskCount());
        executor.setAwaitTerminationSeconds(30);
        executor.shutdown();
        System.out.println("11");
    }

    @Test
    public void testflipRight() {
        String[] ss = {"张三：27", "李四：22"};
        printInfo((s1) -> {//操作一
            String name = s1.split("：")[0];
            System.out.print("姓名：" + name + ",");
        }, (s2) -> {//操作二
            String age = s2.split("：")[1];
            System.out.println("年龄：" + age + "。");
        }, ss);
        //简化：因为s1,s2都代表数组中同一个元素（字符串），使用同一变量。
        printInfo(
                (s) -> System.out.print("姓名：" + s.split("：")[0] + ","),//操作一
                (s) -> System.out.println("年龄" + s.split("：")[1] + "。")//操作二
                , ss);

    }

    public void printInfo(Consumer<String> c1, Consumer<String> c2, String[] arrs) {
        for (String arr : arrs) {
            c1.andThen(c2).accept(arr);
        }
    }

    @Test
    public void testArrayList(){
        List<String> list=new ArrayList<>(1);
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
    }
}
