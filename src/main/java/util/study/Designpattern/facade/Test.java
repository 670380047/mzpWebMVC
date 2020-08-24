package util.study.Designpattern.facade;

/**
 * 【门面模式】
 *      测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/18 20:54
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        // 创建一个门面类。（集成了3个子系统的功能）
        Facade facade = new Facade();

        // 门面类分别调用三个系统
        System.out.println("============电信系统===============");
        facade.phone();
        System.out.println("============商城系统===============");
        facade.shop();
        System.out.println("============数据库系统===============");
        facade.dataSource();


    }
}
