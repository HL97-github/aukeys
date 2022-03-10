package com.aukeys.it.demo;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class UnReentrant {
    // Lock lock = new Lock() {
    // };
    // public void outer(){
    //     lock.lock();
    //     inner();
    //     lock.unlock();
    // }
    // public void inner(){
    //     lock.lock();
    //     //do something
    //     lock.unlock();
    // }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        //将指定的元素插入到此队列中,如果当前没有可用空间，则抛出IllegalStateException。
        boolean add = queue.add(1);//true
        //如果此队列包含指定的元素，则返回 true 。
        boolean contains = queue.contains(1);//true
        //将指定的元素插入到此队列中，如果可以立即执行此操作，而没有违反容量限制，返回true，可指定等待时间
        boolean offer = queue.offer(2);//true
        //检索并删除此队列的头，可指定等待的时间
        Integer poll = queue.poll();//1
        //将指定的元素插入到此队列中，等待空格可用。
        queue.put(3);
        //返回该队列最好可以（在没有存储器或资源约束）接受而不会阻塞
        int i = queue.remainingCapacity();//2147483645
        //从该队列中删除指定元素的单个实例（如果存在）。
        boolean remove = queue.remove(3);//ture
        queue.put(4);
        //检索并删除此队列的头，如有必要，等待元素可用。
        Integer take = queue.take();// 2
        System.out.println(queue);// [4]
    }
}

