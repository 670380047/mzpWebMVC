package util.study.Designpattern.proxy.dynamic;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:48
 * @File : DynamicProxyHandler
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：需要实现 InvocationHandler接口  （在反射包下面）
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 16:48
 */
public class DynamicProxyHandler implements InvocationHandler {
    /**
     * 1.目标对象。就是需要代理的类的实例
     */
    private Object target;

    /**
     * 2.生成动态代理对象。 入参是要生成的那个对象的实例
     * @param target
     * @return
     */
    public <T>  T newProxyInstance(T target){
        this.target = target;

        /**
         * 动态生成对象实例。
         * Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         *      第一个参数：通常与目标对象的类加载器保持一致
         *      第二个参数：目标对象实现了哪些接口。 是一个Class数组
         *      第三个参数：实现了InvocationHandler接口的类的实例。当前类就实现了，所以当前类的对象就是。
         *          目的是为了执行类（实现了InvocationHandler接口）中的invoke()方法。
         */
        ClassLoader classLoader = target.getClass().getClassLoader();  // 获取目标类的类加载器
        Class[] interfaces = target.getClass().getInterfaces(); // 获取目标类实现的接口

        return (T) Proxy.newProxyInstance(classLoader,interfaces,this);
    }

    /**
     * invoke方法 是由Proxy.newProxyInstance(classLoader,interfaces,this) 返回的实例proxy来调用的
     * @param proxy   动态生成的代理对象。 上面返回的proxy
     * @param method    需要代理对象执行的Method实例
     * @param args  执行方法需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------------------代理开始（面向切面编程）---------------");
//        System.out.println(proxy);
        /**
         * 第一个参数target表示调用哪一个实例（target）的方法
         * 第二个参数 args是表示参数列表。
         */
        Object obj = method.invoke(target,args);
        System.out.println("-------------------代理结束（面向切面编程）---------------");
        return obj;  //返回的是实际方法的返回值
    }
}
