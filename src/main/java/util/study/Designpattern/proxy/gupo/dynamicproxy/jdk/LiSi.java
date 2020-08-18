package util.study.Designpattern.proxy.gupo.dynamicproxy.jdk;

/**
 * 【动态代理】
 *      jdk动态代理，接口实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:24
 * @File : LiSi
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class LiSi implements IPerson {
    private String require = "人美心善";

    @Override
    public void findLove() {
        System.out.println("李四的要求："+require);
    }

    @Override
    public void buyCar() {
        System.out.println("李四买了辆奔驰R8");
    }
}
