package util.study.Designpattern.facade;

/**
 * 【门面模式】
 *      门面类：统一集成其他系统
 *
 *      门面类本身不增加任何功能，只负责调用各个子系统。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/18 20:48
 * @File : Facade
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Facade {
    private SubSystemA systemA = new SubSystemA();
    private SubSystemB systemB = new SubSystemB();
    private SubSystemC systemC = new SubSystemC();

    /**
     * 封装电信系统的业务流程
     */
    public void phone(){
        systemA.phone();
        systemA.meal();
        systemA.call();
    }

    /**
     * 封装网上商城系统的流程
     */
    public void shop(){
        systemB.order();
        systemB.pay();
        systemB.transport();
    }

    /**
     * 封装数据库连接系统
     */
    public void dataSource(){
        systemC.connect();
        systemC.statement();
        systemC.execute();
        systemC.close();
    }
}
