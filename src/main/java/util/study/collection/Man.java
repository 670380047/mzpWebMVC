package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/28 15:22
 * @File : Man
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * Person的子类。
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/28 15:22
 */
public class Man extends Person{

    public Man(){}

    public Man(String name, int age){
        this.name = name;  //这两个属性是继承自父类Person的
        this.age = age;     //这两个属性是继承自父类person的
    }

    /**
     * 重写父类的eat方法
     */
    @Override
    public void eat() {
//        super.eat();      // 调用父类的方法。 但是this.getClass().getName() 得到的还是子类Man。
        System.out.println(this.getClass().getName()+":吃饭,我是Man类");
    }
}
