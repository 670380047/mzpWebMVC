package util.testinterface.testClass;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/1/14 16:52
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.testinterface.MyInterface;

/**
 *
 * @Author maozp3
 * @Description: 测试接口的使用，同一个接口有两个实现类，该怎么用？
 * @Date: 2020/1/14 16:52
 */
public class TestMain {

    /**
     * 在spring中。因为@Autowired是默认按照类型注入的，但是一个接口如果同时存在两个实现类，spring该怎么注入呢？
     * 答：可以使用@Autowired 加上 @Qualifier  即按名称注入，来告诉spring该注入哪个类
     * @Autowired
     * @Qualifier("要注入实现类的类名")
     * 期中@Qualifier中的类名是实现类中service注解的名字，如：@Service("自己起的名字")  但是如果没有写名字，如：@Service。
     *      那么就用这个类本身的名字来作为默认的名字（此时默认的名字，是类名的首字母小写格式）
     *
     *      @Autowired
     *      @Qualifier("myClass")
     *
     */


    /**
     * 同一个接口有两个实现类，该怎么用？
     * 答：声明变量的时候，变量类型是接口（myInterface）。  但是传给该变量的对象（new **） 却是可以选择的，
     *      new不同的实现类，那么这个接口变量就是那个实现类了。（一个接口变量可以接受所有实现了这个接口的对象。）
     */
   static MyInterface myInterface = new OtherClass();   // new OtherClass()实现类
//    static MyInterface myInterface = new MyClass();  // new MyClass()实现类

    public static void main(String[] args) {
        myInterface.say();
    }
}
