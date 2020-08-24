package util.study.Designpattern.facade;

/**
 * 【门面模式】
 *      子系统A：简单模拟电信系统
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/18 20:34
 * @File : SubSystemA
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SubSystemA {
    public void phone(){
        System.out.println("手机号开户....");
    }

    public void meal(){
        System.out.println("办理套餐....");
    }

    public void call(){
        System.out.println("使用手机打电话....");
    }
}
