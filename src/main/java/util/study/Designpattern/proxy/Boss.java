package util.study.Designpattern.proxy;

/**
 * 代理模式
 * 静态代理： 老板接口。
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:23
 * @File : Bo
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface Boss {
    /**
     * 见面
     */
    void meet();

    /**
     * 消费
     */
    void consume();

    /**
     * 吃东西
     * @param food
     */
    void eat(String food);
}
