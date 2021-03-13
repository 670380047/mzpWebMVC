package com.example.service;

import com.example.module.UserInfoModule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/13 21:11
 * @File : ThreadPoolDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Service
public class ThreadPoolOne  implements Callable<List> {


    UserInfoModule userInfoMapper;

    ThreadPoolService threadPoolService;

    @Override
    public List call()  {
        List<Map<String, Object>> result = null;
        try {
            /**
             * 因为线程内无法使用Autowire来注入属性。 所以就先获取applicationContext，然后用getBean来获取容器中的对象
             */
            threadPoolService = (ThreadPoolService) ThreadPoolService.getContext().getBean("threadPoolService");
            userInfoMapper = (UserInfoModule) ThreadPoolService.getContext().getBean("userInfoModule");
            result = userInfoMapper.test(3);
        }catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //CountDownLatch减少1
            threadPoolService.getCountDownLatch().countDown();
        }
        return result;
    }
}
