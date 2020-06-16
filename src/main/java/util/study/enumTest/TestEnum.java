package util.study.enumTest;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/16 18:37
 * @File : TestEnum
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * 枚举测试类
 *
 * 一、枚举的创建
 * 二、枚举常用的方法
 *      1. 枚举名.values()   （类似静态方法）该方法会返回在枚举中按照声明顺序产生的常量值组成的数组
 *      2. ordinal()。 (类似实例方法)返回某个枚举常量的索引。从0开始
 * 三、交通信号灯测试。
 * 四、测试枚举的构造方法
 *      1.枚举的构造方法是私有的，所以不能实例化。只能直接使用 “枚举名.属性”  来获取
 *
 * 五、测试EnumMap<key,value>  enumMap = new EnumMap<key, value>(key.class);
 *     1.其中key只能来自同一个枚举。不能既有这个枚举的，也有另一个枚举的
 *     2.实例化的时候，需要传入key对应的那个枚举的class。
 *     3.其他用法同HashMap一样
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/16 18:37
 */
public class TestEnum {

    /**
     * 一、枚举的创建
     */
    @Test
    public void test1(){
        /**
         * 枚举的创建
         */
        EnumSeason season = EnumSeason.SPRING;
        System.out.println(season);  // SPRING
    }

    /**
     * 二、枚举常用的方法
     *      1. 枚举名.values()   （类似静态方法）该方法会返回在枚举中按照声明顺序产生的常量值组成的数组
     *      2. ordinal()。 (类似实例方法)返回某个枚举常量的索引。从0开始
     */
    @Test
    public void test2(){
        /**
         * 1.使用枚举名.values()，获取枚举数组
         */
        EnumSeason[] values = EnumSeason.values();
        for(EnumSeason enumSeason : values){
            System.out.print(enumSeason); // SPRING  SUMMER  AUTUMN  WINTER
            /**
             * 2.使用 枚举实例.ordinal()获取某个枚举值的索引
             */
            int index = enumSeason.ordinal();
            System.out.println("  ,索引值是："+index);
        }
    }



    /**
     * 三、交通信号灯测试。
     */
    @Test
    public void test3(){
        TrafficLight result = TrafficLight.RED;
        for (int i=0;i<10;i++) {
            result = change(result);
            System.out.println(result);
        }
    }
    /**
     * 三、交通信号灯测试。
     */
    public TrafficLight change(TrafficLight light){
        switch(light){
            case RED:  // 枚举成员在case中只能写成员的名字：RED。不能写枚举名.RED ：TrafficLight.RED
                light = TrafficLight.YELLOW;
                break;
            case YELLOW:  // 枚举成员在case中只能写成员的名字：RED。不能写枚举名.RED ：TrafficLight.RED
                light = TrafficLight.GREEN;
                break;
            case GREEN:  // 枚举成员在case中只能写成员的名字：RED。不能写枚举名.RED ：TrafficLight.RED
                light = TrafficLight.RED;
                break;
        }
        return light;
    }

    /**
     * 四、测试枚举的构造方法
     *      1.枚举的构造方法是私有的，所以不能实例化。只能直接使用 “枚举名.属性”  来获取
     */
    @Test
    public void test4(){
        /**
         * 1.获取枚举属性
         */
        Status status = Status.FORBIDDEN;
        System.out.println(status);
        /**
         * 2.通过getter()方法来获取枚举的
         */
        System.out.println(status.getCode());
        System.out.println(status.getValue());

        /**
         * 3.默认枚举不是final的，所以可以通过setter来修改值（建议定义成final的）
         */
        status.setCode(100);
        System.out.println(status);
    }

    /**
     * 五、测试EnumMap<key,value>  enumMap = new EnumMap<key, value>(key.class);
     *     1.其中key只能来自同一个枚举。不能既有这个枚举的，也有另一个枚举的
     *     2.实例化的时候，需要传入key对应的那个枚举的class。
     *     3.其他用法同HashMap一样
     */
    @Test
    public void test5(){
        /**
         * 1.声明变量并创建EnumMap实例
         */
        EnumMap<Status,String> enumMap = new EnumMap<Status, String>(Status.class);
        /**
         * 2.给EnumMap赋值。其中key是Status枚举
         */
        enumMap.put(Status.SPRING,"这是春天啊");
        enumMap.put(Status.WEST,"这是西边");
        enumMap.put(Status.NOT_FOUND,"404？路径怎么又找不到了");

        /**
         * 3.通过entrySet来遍历Map。
         * 遍历map的三中方式在这里都可以遍历。===》../collection/TestMap类中
         */
        Set<Map.Entry<Status, String>> entrySet = enumMap.entrySet();
        for(Map.Entry<Status, String> entry :  entrySet){
            Status key = entry.getKey();  // 获取EnumMap的可以，因为这个key是个枚举，所以他也有很多个属性，比如code和value
            System.out.println("EnumMap的key = "+key);
            System.out.println("key.getCode = "+key.getCode());
            System.out.println("key.getValue = "+key.getValue());

            String value = entry.getValue();  // 获取EnumMap的value值
            System.out.println("EnumMap的value = "+value);
            System.out.println("------------------------------------------");
        }
    }

}
