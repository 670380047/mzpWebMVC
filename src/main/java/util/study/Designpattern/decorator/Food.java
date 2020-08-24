package util.study.Designpattern.decorator;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:47
 * @File : Food
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 【装饰者模式】：
 *      食物接口：规范
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:47
 */
public interface Food {


    public String make();

    public Food addFood();

}
