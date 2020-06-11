package util.study.Designpattern.observer;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:22
 * @File : Staff2
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:22
 */
public class Staff2 implements Person{
    String name ;

    public Staff2() {
    }

    public Staff2(String name) {
        this.name = name;
    }

    @Override
    public void getMessage(String message) {
        System.out.println(name+"收到消息："+message);
    }
}
