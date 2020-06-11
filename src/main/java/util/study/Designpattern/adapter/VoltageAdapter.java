package util.study.Designpattern.adapter;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 12:40
 * @File : VoltageAdapter
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 适配器模式：充电器
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/3 12:40
 */
public class VoltageAdapter implements Adapter{

    @Override
    public void changeVoltage(Phone phone){
        System.out.println(phone.getPhoneName()+"正在充电...");
        System.out.println("转换前的电压："+(V));
        System.out.println("转换后的电压："+phone.getV());
        System.out.println(".......一段时间之后......");
        System.out.println(phone.getPhoneName()+"充电完成......");
    }

//    @Override
//    public void setV(int newV) {
//        V = newV;
//    }
}
