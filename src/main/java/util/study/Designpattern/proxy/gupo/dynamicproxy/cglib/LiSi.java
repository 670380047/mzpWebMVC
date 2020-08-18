package util.study.Designpattern.proxy.gupo.dynamicproxy.cglib;

/**
 * 【代理模式】
 *      cglib动态代理代理实现类：李四
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:24
 * @File : LiSi
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class LiSi  {
    private String require = "人美心善";


    public void findLove() {
        System.out.println("李四的要求："+require);
    }


    public void buyCar() {
        System.out.println("李四买了辆奔驰R8");
    }
}
