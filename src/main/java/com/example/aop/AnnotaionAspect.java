package com.example.aop;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/12 16:15
 * @File : AnnotaionAspect
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/12 16:15
 */
@Aspect   //声明为切面
@Service   //一定要把这个类注册为bean，交给spring管理
public class AnnotaionAspect {

    ThreadLocal<Long> spendTime = new ThreadLocal<>();
    //这个切点表达式精确到了具体方法
    @Around("execution(* com.example.controller.TestController.testAspect(..))")
//    @Around("execution(* com.example.aop.AopTestController.aopTest(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================注解方式切面启动(around)：日志=================");
        System.out.println("mzpLog:"+joinPoint.getSignature().getName());
        //这个joinPoint.proceed()是用于主业务核心代码。@Aroud注解最后是要返回这个object对象的
        Object object = joinPoint.proceed();
        System.out.println("mzpLog:"+joinPoint.getSignature().getName());
        System.out.println("=================注解方式切面结束(around)：日志=================");
        return object;
    }

    @Before("execution(* com.example.controller.TestController.testAspect(..))")
    public void before() throws Throwable {
        System.out.println("=================注解方式切面启动(before)：日志=================");

        System.out.println("=================注解方式切面结束(before)：日志=================");
        return ;
    }

    @Around("execution(* com.example.controller.TestController.testAspect(..))")
    public Object spendTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================注解方式切面启动(around)：计算时间=================");
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
        System.out.println("=================注解方式切面结束(around)：计算时间=================");
        return object;
    }

//=======================================@Pointcut注解使用=========================================

    //使用@Pointcut定义一个方法（返回值为空，方法体也可以不写东西）作为切点"代表"。然后其他切面（比如@Before）都可以引用这个切点，只需要引用这个方法名就好了
    @Pointcut("execution(* com.example.aop.AopTestController.aopTest(..))")
    public  void myPointcut(){
        System.out.println("@Pointcut注解测试：（这个方法体似乎没用，方法体可不写）最终通知，释放资源。来自myPointcut方法");
    }

    /*上面的@Pointcut注解了一个切点，然后其他的@Before、@After、@Around、@AfterReturning....这些注解只需
    *要引用
    *@Pointcut所注解的那个方法就好了。而不需要再写切点表达式
    * */
    @After("myPointcut()")  //只需要引用@Pointcut所注解的那个方法(myPointcut)就好了
    public void after()
    {
        System.out.println("@Pointcut注解测试：最终通知，释放资源。来自after方法");
    }


    @Before("myPointcut()")  ////只需要引用@Pointcut所注解的那个方法(myPointcut)就好了
    public void beforeAOP()
    {
        System.out.println("@Pointcut注解测试：最终通知，释放资源。来自beforeAOP方法");
    }

    @Around("myPointcut()")  ////只需要引用@Pointcut所注解的那个方法(myPointcut)就好了。@Aroud注解最后是要返回这个object对象的
    public Object aroundAOP(ProceedingJoinPoint joinPoint)
    {
        Object object = null;
        try {
            object = joinPoint.proceed();  //主业务逻辑。 @Aroud注解最后是要返回这个object对象的
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("@Pointcut注解测试：最终通知，释放资源。来自aroundAOP方法");
        return  object;
    }


}
