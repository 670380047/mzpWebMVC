package com.example.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/13 21:03
 * @File : ThreadPoolService
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Service
public class ThreadPoolService implements ApplicationContextAware {

     static ApplicationContext context;

     @Autowired
     ThreadPoolAll threadPoolAll;

    /**
     * 定义线程池的参数
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,0l, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
    /**
     * 定义一个CountDownLatch类。
     */
    CountDownLatch countDownLatch ;

    public List getThreadPool(int n){
        List listUserInfo = null;
        List listOne = null;
        try {
            /**
             * 初始化 CountDownLatch。
             */
            countDownLatch = new CountDownLatch(n);

            Future<List> submit = threadPoolExecutor.submit(threadPoolAll.getInstance());
            Future<List> submit1 = threadPoolExecutor.submit(new ThreadPoolOne());
            /**
             * 在线程任务执行完之前，阻塞队列。
             */
            countDownLatch.await();
            listUserInfo = submit.get();
            listOne = submit1.get();
            listUserInfo.addAll(listOne);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUserInfo;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
