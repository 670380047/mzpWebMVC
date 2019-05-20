package com.example.interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/19 15:44
 * @File : HandlerInterceptor1
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Service
public class HandlerInterceptor1 extends HandlerInterceptorAdapter {
    int preNum=0;
    int postNum=0;
    int afterNum=0;
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //开始时间
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        preNum++;
        System.out.println("HandlerInterceptor1开启预处理监听。。。。。"+preNum);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        postNum++;
        System.out.println("HandlerInterceptor1开启后处理监听。。。。。"+postNum);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //结束时间
        long endTime = System.currentTimeMillis();
        Long startTime = startTimeThreadLocal.get();
        Long consumeTime = endTime-startTime;
        afterNum++;
        System.out.println(request.getRequestURI()+"整个请求处理完毕回调。。。。。"+afterNum+"用时："+consumeTime+"毫秒");
        HttpSession session = request.getSession();

    }
}
