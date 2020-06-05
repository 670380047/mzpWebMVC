package util.study.reflect.proxy;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:27
 * @File : CoalSecretary
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *  代理模式
 *  静态代理： 煤老板的秘书类。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 16:27
 */
public class CoalSecretary implements Secretary ,Boss{

    private CoalBoss coalBoss;

    public CoalSecretary(){
        coalBoss = new CoalBoss();
    }



    @Override
    public void meet() {
        System.out.println("------煤老板的秘书安排与老板见面了start-----");
        coalBoss.meet();
        System.out.println("------煤老板的秘书安排老板见面结束了end-----");
    }

    @Override
    public void consume() {
        System.out.println("------煤老板的秘书安排老板消费了start-----");
        coalBoss.consume();
        System.out.println("------煤老板的秘书安排老板消费结束了end-----");
    }

    @Override
    public void eat(String food) {
        System.out.println("------煤老板的秘书安排老板吃饭开始了start-----");
        coalBoss.eat(food);
        System.out.println("------煤老板的秘书安排老板吃饭结束了end-----");
    }
}
