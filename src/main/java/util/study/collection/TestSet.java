package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/20 17:07
 * @File : TestSet
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * * 集合框架
 *         java.util.Collection 接口：集合层次的根接口
 *              1.java.util.List 接口: 有序的，允许重复的。因为List体系的集合都有索引值
 *                  1.1 java.util.ArrayList：采用“数组”结构存储数据
 *                        优点：查询效率高，因为有索引值
 *                        缺点：增加和删除效率低，因为要前后移动元素
 *                  1.2 java.util.LinkedList：采用“链表”结构存储数据（双向链表）。链表结构用前后节点索引来连接各个元素
 *                        优点：增加和删除效率高，只需要维护前后节点就好了
 *                        缺点：查询效率低，要遍历
 *                  1.3 java.util.Vector(不常用)
 *              2.java.util.Set 接口: 存储无序，不允许重复。  存储使用的是hash表，每个hash码位置固定，元素的根据自己的hashCode不同，来存入不同的位置。
 *                  2.1 java.util.HashSet: 是Set接口的典型实现类。
 *                          2.1.1判断元素是否存在的依据：先根据hashCode(哈希码)判断，再根据equals()方法判断内容。
 *                              即：如果使用hashCode的时候，对象要重写hashCode()方法可以保证相同内容的对象，产生hashCode是相同的。 否则，不同对象的hashCode就不同。
 *                                  再重写 equals方法的话（请参照util.study.collection.Person类），可以保证相同内容的对象是相等的。 否则，内容相同，不保证元素相同
 *                                  （根据需要，选择是否重写这两个方法）
 *                          2.1.2使用Hash算法：每个对象生成的时候，jvm都会根据他的内容值来给他赋了一个hashCode（哈希码），可以理解为内存的地址（但他并不是内存的地址）
 *                                  内存中存在一张哈希码表，而哈希码值在哈希码表中的位置是固定的。
 *                                  当一个对象要存入HashSet中的时候，先根据这个对象的哈希码值去内存的哈希表中查找，看这个位置有没有对象在
 *                                  如果没有，就直接存进去；如果有，就在根据equals()方法去对比这两个对象的内容是否相等，如果内容相等，就存不进去（此时需要对象重写equals方法）。
 *                                  如果根据equals()两个内容不一样，那就会在这个哈希码对应的地址处再放入一个元素，多个元素以数组的方式存在。
 *
 *                          注意：如果一个对象重写了hashCode方法的话，那么只要对象的内容一样，他们生成的hashCode就会一样，类似String,String就重写了hashCode()
 *                               如果此时又重写了equals方法，来保证内容一样，就表示相同对象的话，就能保证同一个内容相同的对象不会被重复放进HashSet中。
 *
 *                          2.1.3 java.util.LinkedHashSet: 是HashSet的子类。比hashSet多了一个链表来维护相邻元素的前后关系（便于遍历）。
 *                              2.1.3.1 LinkedHashSet在存储位置上同样是无序的，同样采用hash表。 只是多了一个链表来维护前后关系，所以给人的感觉是有序的
 *                              2.1.3.2: 效率对比： HashSet 增、删等修改操作效率高。
 *                                             LinkedHashSet遍历效率高，因为有链表方便查找相邻元素。 增删操作效率相对低，因为多了对链表的处理。
 *                              2.1.3.3 没有自己的方法，全是继承父类的，用法和HashSet一模一样。
 *                  2.2 java.util.TreeSet:
 *
 * 一、HashSet
 * 二、linkedHashSet
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/20 17:07
 */
public class TestSet {

    /**
     * 一、HashSet
     */
    @Test
    public void test1(){
        HashSet hashSet = new HashSet();

        /**
         * 存储是无序的，和添加顺序无关。
         */
        hashSet.add("aa");
        hashSet.add("bb");
        hashSet.add("cc");
        hashSet.add("dd");
        hashSet.add("aa");
        hashSet.add("mzp");
        hashSet.add(new Person("张三",18));   //如果没重写，会重复出现，因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了）
        hashSet.add("aa");
        hashSet.add(new String("aa"));
        hashSet.add(new Person("张三",18));     //如果没重写，会重复出现，因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了）

        Person person = new Person("李四",20);
        Person person1 = new Person("李四",20);
        System.out.println("person（李四）的哈希码："+person.hashCode());
        // 此处添加失败，因为重写hashCode方法和equals方法后，hashSet认为他俩内容相同就是同一个对象了。
        System.out.println("person1（李四）的哈希码："+person1.hashCode());

        // 如果没重写，就会重复出现。因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了。这里Person已经重写了，所以不会重复出现）
        System.out.println("person(李四)添加成功了？："+hashSet.add(person));
        // 如果没重写，就会重复出现。因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了。这里Person已经重写了，所以不会重复出现）
        System.out.println("person1(李四)添加成功了？："+hashSet.add(person1));


        String str1 = new String("admin");
        String str2 = new String("admin");
        System.out.println("str1(admin)的哈希码："+str1.hashCode());
        // 此处添加失败，因为重写hashCode方法和equals方法后，hashSet认为他俩内容相同就是同一个对象了。
        System.out.println("str2(admin)的哈希码："+str2.hashCode());

        System.out.println("str1(admin)添加成功了？："+hashSet.add(str1));
        System.out.println("str2(admin)添加成功了？："+hashSet.add(str2));

        System.out.println(hashSet);
    }

    /**
     * 二、linkedHashSet
     */
    @Test
    public void test2(){
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("aa");
        linkedHashSet.add("cc");
        linkedHashSet.add("bb");
        linkedHashSet.add("dd");
        linkedHashSet.add("123");
        linkedHashSet.add("cc");
        System.out.println(linkedHashSet);
    }

}
