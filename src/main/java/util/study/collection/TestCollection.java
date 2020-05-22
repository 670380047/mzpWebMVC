package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 14:22
 * @File : TestCollection
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 一、集合：就像是一种容器，用于存储、获取、操作队形的容器
 *      集合的特点：
 *          1.集合的长度是可变带的
 *          2.集合中可以存储任意类型的对象
 *          3.集合只能存储对象类型。不能存储基本类型如int（包装类型可以：如Integer。集合有自动装箱的功能）。
 *      数组相对于集合的弊端：
 *          1.数组的长度不可变
 *          2.数组没有提供可以查看元素有效个数的办法。数组需要定义一个total来统计个数，新增就+1，删除就-1
 * 二、集合框架
 *     java.util.Collection 接口：集合层次的根接口
 *          1.java.util.List 接口: 有序的，允许重复的。因为List体系的集合都有索引值
 *          2.java.util.Set 接口: 无序，不允许重复。
 *
 * 集合测试：
 * 一、add()  、 size() 、  clear()  、  isEmpty()
 *
 * 二、addAll() 、 contains(Object obj)、 containsAll(Collection<?> c)
 *      retainAll(Collection c)：取交集
 *      remove(Object obj)、      removeAll(Collection<?> c)：取差集
 *      toArray()
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 14:22
 */
public class TestCollection {

    /**
     * 一、add()  、 size() 、  clear()  、  isEmpty()  测试
     */
    @Test
    public void test1(){
        Collection collection = new ArrayList();

        /**
         * 1. add(Object obj): 添加任意对象到集合中
         */
        collection.add("集合字符串");
        collection.add(true);
        collection.add(123);
        collection.add(new Person("张三",18));
        System.out.println(collection);

        /**
         * 2. size() : 获取集合中有效元素的个数
         */
        int s = collection.size();
        System.out.println("集合中元素的个数："+s);

        /**
         * 3. clear() :清空集合中的所有元素
         */
        collection.clear();
        /**
         * 4.  isEmpty() :判断集合是否为空，里面是否有元素
         */
        boolean b = collection.isEmpty();
        System.out.println("集合是否为空："+b);
        System.out.println(collection);
    }

    /**
     * 二、addAll() 、 contains(Object obj)、  containsAll(Collection<?> c)
     *      retainAll(Collection c)：取交集
     *      remove(Object obj)、      removeAll(Collection<?> c)：取差集
     *      toArray()
     *
     */
    @Test
    public void test2(){
        Collection collection = new ArrayList();

        collection.add("集合字符串");
        collection.add(true);
        collection.add(123);
        collection.add(new Person("张三",18));
        System.out.println(collection);

        System.out.println("---------------------addAll()---------------------");
        /**
         * 通过数组的  Arrays.asList(T... t)来创建一个集合
         */
        Collection collection2 = Arrays.asList(1,2,new Person("李四",20));
        /**
         * 1. addAll(Collection c): 把一个集合中的元素一个一个取出来，放入另一个元素中。
         *      add(一个集合)： 会把参数里的集合整体当做一个元素，来放入新集合中。
         */
        collection.addAll(collection2);
//        collection.add(collection2);
        System.out.println(collection);

        System.out.println("---------------------contains(Object obj)---------------------");
        /**
         * 2. contains(Object obj): 判断集合中是否存在指定元素
         *  判断元素是否存在的依据是 equals() 。实体类可以重写 equals()
         */
        boolean b1 = collection.contains("集合字符串");
        System.out.println("'集合字符串'元素是否存在："+b1);

        boolean b2 = collection.contains(new Person("张三",18));
        System.out.println("对象是否存在："+b2);

        System.out.println("---------------------containsAll(Collection<?> c)---------------------");
        /**
         * 3. containsAll(Collection<?> c) :  判断集合 c中所有的元素 是否存在于另一个集合中
         */
        boolean b3 = collection.containsAll(collection2);
        System.out.println("判断一个集合是否包含在另一个集合中："+b3);

        System.out.println("---------------------retainAll(Collection c)  取交集---------------------");
        /**
         * 4. retainAll(Collection c): 取交集。返回布尔类型，如果成功，调用者就会变成交集。否则就是空元素的集合
         */
        boolean b4 = collection.retainAll(collection2);
        System.out.println("取交集："+b4);
        System.out.println(collection);

        System.out.println("---------------------给集合重新赋值了一下---------------------");
        /**
         * 重新赋值一下，为了下面的测试结果顺利。
         */
        collection.add("集合字符串");
        collection.add(true);
        collection.add(123);
        collection.add(new Person("张三",18));
        collection.addAll(collection2);
        System.out.println(collection);

        System.out.println("---------------------remove(Object obj)---------------------");
        /**
         * 5.remove(Object obj)  :删除指定元素
         */
        boolean b5 = collection.remove(123);
        System.out.println("删除元素数字123:"+b5);
        System.out.println(collection);

        System.out.println("---------------------removeAll(Collection<?> c) 取差集---------------------");
        /**
         * 6.removeAll(Collection<?> c)  : 删除集合中所包含的另一个集合c中的元素
         *      取差集，减去公共部分，与 retainAll 取交集相反
         */
        boolean b6 = collection.removeAll(collection2);
        System.out.println("removeAll处理成结果："+b6);
        System.out.println(collection);

        System.out.println("---------------------toArray()---------------------");
        /**
         * 7. toArray():  把集合变为数组
         *      把数组变为集合：  Arrays.asList(T... t)
         */
        Object[] objects = collection.toArray();
        for(Object o : objects){
            System.out.print(o+"   ");
        }
    }
}
