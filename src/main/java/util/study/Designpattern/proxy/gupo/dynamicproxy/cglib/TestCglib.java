//package util.study.Designpattern.proxy.gupo.dynamicproxy.cglib;
//
//import net.sf.cglib.core.DebuggingClassWriter;
//
///**
// * 【代理模式】
// *      动态代理：cglib
// *
// * @version: java version 1.7+
// * @Author : mzp
// * @Time : 2020/8/15 21:11
// * @File : TestCglib
// * @Software: IntelliJ IDEA 2019.2.04
// */
//public class TestCglib {
//    public static void main(String[] args) {
//        // 这个而是利用cglib的方法，把cglib生成的类显示出来，放在磁盘的某个位置。
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"H:/cglibTem");
//        CglibProxy cglibProxy = new CglibProxy();
//        ZhangSan zhangSanProxy = (ZhangSan) cglibProxy.getInstance(ZhangSan.class);
//        zhangSanProxy.findLove();
//    }
//
//}
