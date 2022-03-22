package com.example.service;

import com.example.dao.IUserInfoMapper;
import com.example.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *  因为spring无法给线程注入依赖（无法给实现Callable接口的类注入依赖）。
 *
 *  所以这里采用内部类的方式。
 *      1.即使用内部类来实现Callable接口。
 *      2.然后在就可以在外部类里面注入依赖了
 *      3.把外部类自动注入的依赖，通过构造方法给内部类穿进去，同时可以获取内部类的对象
 *      4.这个对象可以自己直接.start启动线程。也可以交给线程池来做。
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/13 22:08
 * @File : ThreadPoolAll
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Service
public class ThreadPoolAll  {
    /**
     * 2.外部类注入依赖。这两个依赖都是内部类需要的
     *
     * 一个是Dao层的依赖。
     * 另一个是调用getCountDownLatch().countDown()方法所需要的依赖
     */
    @Autowired
    IUserInfoMapper userInfoMapper;
    @Autowired
    ThreadPoolService threadPoolService;

    /**
     * 3.1 获取内部类的对象。同时把外部类注入依赖通过构造方法传递给内部类
     * @return
     */
    public InnerThreadPoolAll getInstance(){
        //通过构造参数把注入的两个依赖给线程传进去。
        return new InnerThreadPoolAll(userInfoMapper,threadPoolService);
    }

    /**
     * 1. 使用内部类实现Callable接口。
     * 在内部类里面实现call()方法，即线程需要的处理的逻辑
     */
    class InnerThreadPoolAll implements Callable<List>{

        IUserInfoMapper userInfoMapper;
        ThreadPoolService threadPoolService;

        /**
         * 3.2 通过构造方法类接收外部类传递过来的依赖。
         *      因为我们把内部类需要的依赖，放到外部类中，由spring去注入了
         * @param userInfoMapper
         * @param threadPoolService
         */
        public InnerThreadPoolAll(IUserInfoMapper userInfoMapper, ThreadPoolService threadPoolService) {
            this.userInfoMapper = userInfoMapper;
            this.threadPoolService = threadPoolService;
        }

        @Override
        public List call()  {
            List<UserInfo> userInfos = null;
            try {
                userInfos = userInfoMapper.selectUserInfoAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //CountDownLatch减少1
                threadPoolService.getCountDownLatch().countDown();
            }
            return userInfos;
        }
    }
}
