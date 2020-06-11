package util.study.Designpattern.adapter;

/**
 * 适配器的接口（例子中没有这个接口，是我自己添加的）
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 12:46
 * @File : Adapter
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface Adapter {

    /**
     * 正常电压220v，是一个常量
     * 接口中默认就是 公共、静态、final
     */
    public static final int V = 220;

    /**
     * 充电的接口
     * @param V  需要的电压
     */
    public void changeVoltage(Phone phone);

//    void setV(int newV);
}
