package util.study.reflect.proxy;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:36
 * @File : SteelSecretary
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 代理模式
 *  静态代理， 钢铁老板秘书类。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 16:36
 */
public class SteelSecretary implements Secretary,Boss{

    private SteelBoss steelBoss;

    public SteelSecretary(){
        steelBoss = new SteelBoss();
    }

    @Override
    public void meet() {
        System.out.println("------钢铁老板的秘书安排与老板见面了start-----");
        steelBoss.meet();
        System.out.println("------钢铁老板的秘书安排老板见面结束了end-----");
    }

    @Override
    public void consume() {
        System.out.println("------钢铁老板的秘书安排老板消费了start-----");
        steelBoss.consume();
        System.out.println("------钢铁老板的秘书安排老板消费结束了end-----");
    }

    @Override
    public void eat(String food) {
        System.out.println("------钢铁老板的秘书安排老板吃饭开始了start-----");
        steelBoss.eat(food);
        System.out.println("------钢铁老板的秘书安排老板吃饭结束了end-----");
    }
}
