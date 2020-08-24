package util.study.Designpattern.facade;

/**
 * 【门面模式】
 *      子系统B： 简单模拟网上商城
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/18 20:42
 * @File : SubSystemB
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SubSystemB {

    public void order(){
        System.out.println("开始下单了....");
    }

    public void pay(){
        System.out.println("付钱了.....");
    }

    public void transport(){
        System.out.println("开始运输，发货了......");
    }
}
