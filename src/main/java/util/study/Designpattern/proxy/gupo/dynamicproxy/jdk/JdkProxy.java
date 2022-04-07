package util.study.Designpattern.proxy.gupo.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 【代理模式】
 *      动态代理；代理实现类.
 *          需要实现InvocationHandler接口，实现其中的invoke方法
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:43
 * @File : JdkProxy
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JdkProxy implements InvocationHandler {
    // 原对象，也就是被代理的对象
    private Object targetInstance;


    public  <T extends IPerson> T getInstance(T targetInstance){
        // 保存一下原对象
        this.targetInstance = targetInstance;

        Class<?> clazz = this.targetInstance.getClass();
        /**
         * 第一个参数是：被代理类的类加载器
         * 第二个参数是：被代理类实现的接口。是一个Class数组
         * 第三个参数是：实现了InvocationHandler接口的对象。也就是本类的实例this。
         *              目的是为了执行类（实现了InvocationHandler接口）中的invoke()方法。
         *
         * 这里最后返回的是一个代理对象，如：$Proxy0。 第三个入参this，会成为这个代理对象的一个属性，保存在变量h中。
         */
        // 在最后这里返回的只能是接口类型。而不能是实现类。否则会报类型转换错误.
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    /**
     *
     * @param proxy 新的代理对象。这个就是最后的代理对象， 如：$Proxy0
     * @param method    代理对象执行的方法。（这个方法是原对象的方法）
     * @param args      代理对象调用的方法的实际参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //实际方法执行后的返回结果
        Object result;
        // 调用前的增强
        before();
//        System.out.println(JdkProxy.class.getMethod("invoke",Object.class,Method.class,Object[].class).getReturnType().getName());
        // 调用正常的业务逻辑
        // 这里的一个参数，传入的是一个对象，这个对象是谁，就决定这个方法是哪个对象实例的"原方法"了。
        result = method.invoke(this.targetInstance,args);
        // 调用后的增强
        after();

        return result;
    }

    //前置增强
    void before(){
        System.out.println("媒婆开始帮儿子找对象了，开始筛选一下.......");
    }
    //后置增强
    void after(){
        System.out.println("找到了。俩人开始尝试交往.....");
    }
}
