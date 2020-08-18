package util.study.Designpattern.proxy.gupo.dynamicproxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * 【代理模式】
 *      动态代理
 *      测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 15:02
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 1.创建一个代理类的实例
         */
        JdkProxy jdkProxy = new JdkProxy();
        /**
         * 2.把“被代理对象”传进去，会获得一个代理对象
         *      注意：返回之类型必须是接口类型，不能是实现类
         */
        IPerson zhangSan = jdkProxy.getInstance(new ZhangSan());
        /**
         * 3.接下来调用接口中的方法即可（实际是通过代理对象调用的“被代理对象”中的方法）
         */
        zhangSan.findLove();
        zhangSan.buyCar();

        System.out.println("==================================");
        IPerson liSi = jdkProxy.getInstance(new LiSi());
        liSi.findLove();
        liSi.buyCar();

        System.out.println("===================生成一个动态代理类的class文件=====================");
        String path = "H:/Proxy0.class";
        // 把指定的类变为字节。
        // 第一个参数是类名： $符号开头是代理生成的类; 然后Proxy0 表示代理生成的第一个类
        // 第二个参数是表示接口
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IPerson.class});
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
            System.out.println("代理类字节码文件打印到磁盘中了:"+path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
