package util.study.Designpattern.adapter;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 12:39
 * @File : Phone
 * @Software: IntelliJ IDEA 2019.3.15
 */

/** 适配器模式： 手机类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/3 12:39
 */
public class Phone {
    /**
     * 手机需要的电压20v，是一个常量
     */
    public  final int V = 20;

    /**
     * 充电器的适配器
     */
    private Adapter adapter;

    private String phoneName; //手机名称

    public Phone() {
    }

    public Phone(String phoneName) {
        this.phoneName = phoneName;
    }

    /**
     * 充电。调用适配器的某个方法。
     */
    public void charge(){
        // 把当前对象传进来（this）
        adapter.changeVoltage(this);
    }

    /**
     * 设置充电器。（这里就是适配器）
     * 这里入参采用了多态的方式。 （入参是父类接口，将来传入的是接口的实现类）
     * @param adapter
     */
    public void setAdapter(Adapter adapter){
        this.adapter = adapter;
    }



    public Adapter getAdapter() {
        return adapter;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public int getV() {
        return V;
    }
}
