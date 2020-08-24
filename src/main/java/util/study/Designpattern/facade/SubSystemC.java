package util.study.Designpattern.facade;

/**
 * 【门面模式】
 *      子系统C：模拟数据库连接
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/18 20:45
 * @File : SubSystemC
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SubSystemC {
    public void connect(){
        System.out.println("建立数据库连接.....");
    }

    public void statement(){
        System.out.println("预编译sql语句.....");
    }

    public void execute(){
        System.out.println("执行sql语句......");
    }


    public void close(){
        System.out.println("关闭数据库连接。");
    }
}
