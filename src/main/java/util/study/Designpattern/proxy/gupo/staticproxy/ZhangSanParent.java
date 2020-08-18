package util.study.Designpattern.proxy.gupo.staticproxy;

/**
 * 【代理模式】
 *          静态代理实现类：张三的父亲
 *          静态代理的缺点：无法做到自适应：无法做到想帮谁代理就帮谁代理。比如现在，张三的父亲就只负责给张三找对象。其他人不管
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:11
 * @File : ZhangSanParent
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ZhangSanParent implements IPerson{

    // 儿子张三的引用。
    IPerson zhangSan;

    public ZhangSanParent() {
    }

    // 构造函数，给变量赋值
    public ZhangSanParent(IPerson zhangSan) {
        this.zhangSan = zhangSan;
    }

    //代理方法。 父亲忙着替儿子去找对象：底层调用儿子的“找对象方法”
    @Override
    public void findLove() {
        // 帮儿子找对象之前，父亲做了一些其他的筛选
        before();

        // 调用儿子找对象的要求
        zhangSan.findLove();

        // 对象找到之后，让他们尝试交往
        after();
    }

    void before(){
        System.out.println("张三的父亲开始帮儿子找对象了，开始筛选一下.......");
    }
    void after(){
        System.out.println("找到了。俩人开始尝试交往.....");
    }
}
