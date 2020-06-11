package com.example.aop;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/12 12:02
 * @File : MyAspect
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *  aop切面
 * @author maozp3
 * @description:
 * @date: 2019/10/12 12:02
 */
public class MyAspect {
    ThreadLocal<Long> spendTime = new ThreadLocal<>();

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================配置方式切面启动：日志=================");
        System.out.println("mzpLog:"+joinPoint.getSignature().getName());
        //这个joinPoint.proceed()是用于主业务核心代码。
        Object object = joinPoint.proceed();
        System.out.println("mzpLog:"+joinPoint.getSignature().getName());
        System.out.println("=================配置方式切面结束：日志=================");
        return object;
    }

    public Object spendTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================配置方式切面启动：计算时间=================");
        Long startTime = System.currentTimeMillis();
        spendTime.set(startTime);
        //获取主业务中方法处的入参
        Object args = joinPoint.getArgs();
        /*
        //创建一个Object类型的数组，里面只有一个map元素，  这个map里面有两个键值对
        Object[] myargs = new Object[]{   new HashMap(){{put("username","mzp");put("password","123");}}   };
        //proceed()中可以传入一个Object类型的数组进去。然后用这个数组去改变主业务中的入参。功能强大！
        Object object = joinPoint.proceed(myargs);
        */
        Object object = joinPoint.proceed();
        System.out.println("参数是："+args.toString());
        Long resultTime = System.currentTimeMillis()-spendTime.get();
        System.out.println(joinPoint.getSignature().getName()+"方法耗时："+resultTime+"毫秒");
        System.out.println("=================配置方式切面结束：计算时间=================");
        return object;
    }

    public Object spendTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================配置方式切面启动：计算时间=================");
        Long startTime = System.currentTimeMillis();
        spendTime.set(startTime);
        Object object = joinPoint.proceed();
        Object args = joinPoint.getArgs();
        System.out.println("参数是："+args);
        Long resultTime = System.currentTimeMillis()-spendTime.get();
        System.out.println(joinPoint.getSignature().getName()+"方法耗时："+resultTime+"毫秒");
        System.out.println("=================配置方式切面结束：计算时间=================");
        return object;
    }
}
