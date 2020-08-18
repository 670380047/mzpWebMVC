package util.study.Designpattern.proxy.gupo.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor ;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 【代理模式】
 *      动态代理，cglib实现。
 *      cglib的实现是通过继承“被代理类”，然后重写被代理类的方法来完成的。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 20:53
 * @File : CglibProxy
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class CglibProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){
        //创建一个增强类Enhancer实例，来设置代理类的属性
        Enhancer enhancer = new Enhancer();
        /**
         * 设置父类为“被代理类”。
         *      即以“被代理类”为模型创建一个子类
         */
        enhancer.setSuperclass(clazz);
        /**
         * 设置当前类为回调类
         *
         */
        enhancer.setCallback(this);
        /**
         * 创建代理类，并返回
         */
        return enhancer.create();
    }

    /**
     *  同jdk代理中的invoke方法作用一样。当我们调用正常的“被代理对象”的方法时，就会转到这里
     * @param o     这个是新生成的代理对象实例。 就是“被代理类”的子类
     * @param method    代理类中的原方法
     * @param objects   方法的参数
     * @param methodProxy   新生成的方法的代理。用这个方法来调用invokeSuper(对象，参数列表)  来调用方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        // 前置增强
        before();
//        method.invoke(o.getClass().getSuperclass().newInstance(),objects);  // 这个是原方法通过反射来调用。

        // 正常的业务逻辑
        // 注意，这里调用的是invokeSuper 而不是 invoke
        result = methodProxy.invokeSuper(o,objects);

        //后置增强
        after();

        return result;
    }

    void before(){
        System.out.println("媒婆开始帮儿子找对象了，开始筛选一下.......");
    }
    void after(){
        System.out.println("找到了。俩人开始尝试交往.....");
    }
}
