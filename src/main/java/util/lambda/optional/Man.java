package util.lambda.optional;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/8 17:02
 * @File : Man
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.Optional;

/**
 * 测试Optional
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 17:02
 */
public class Man {
    private String name;
    private Optional<Goddess> goddess = Optional.empty();       // Optional初始定义为一个空的Optional对象，

    public Man() {
    }

    public Man(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public Man(String name, Optional<Goddess> goddess) {
        this.name = name;
        this.goddess = goddess;
    }

    public Optional<Goddess> getGoddess() {
        return goddess;
    }

    public void setGoddess(Optional<Goddess> goddess) {
        this.goddess = goddess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' +
                ", goddess=" + goddess +
                '}';
    }
}
