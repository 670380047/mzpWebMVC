package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 15:37
 * @File : TestList
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 集合框架
 *       java.util.Collection 接口：集合层次的根接口
 *            1.java.util.List 接口: 有序的，允许重复的。因为List体系的集合都有索引值
 *                1.1 java.util.ArrayList：采用“数组”结构存储数据
 *                      优点：查询效率高，因为有索引值
 *                      缺点：增加和删除效率低，因为要前后移动元素
 *                1.2 java.util.LinkedList：采用“链表”结构存储数据（双向链表）。链表结构用前后节点索引来连接各个元素
 *                      优点：增加和删除效率高，只需要维护前后节点就好了
 *                      缺点：查询效率低，要遍历
 *                1.3 java.util.Vector(不常用): 用法和ArrayList一样，但是Vector是线程安全的
 *            2.java.util.Set 接口: 无序，不允许重复。
 *
 *  ArrayList的常用方法:
 *      void add(int index, Object ele)     //新增
 *      boolean addAll(int index, Collection eC)      //新增
 *      Object get(int index)       //根据索引获取
 *      int index0f(object obj)     //从前查找指定元素的索引
 *      int lastIndex0f(object obj)     // 从后查找指定元素的索引
 *      Object remove(int index)        //根据索引删除元素
 *      object set(int index, Object obj)   // 根据索引修改指定位置的元素
 *      List subList(int fromIndex, int toIndex)     // 根据索引截取一个子list： 包含头，不包含尾。 [ )  前闭后开区间
 *
 *   LinkedList的常用方法：
 *      addFirst()      同  push(E e)
 *      addLast()
 *      getFirst()
 *      getLast()
 *
 *      removeFirst()       // 移除并返回首元素,如果集合为空(空元素)，就抛出异常
 *      removeLast ()       //移除并返回尾元素,如果集合为空(空元素)，就抛出异常
 *      poll()              //移除并返回首元素,如果集合为空(空元素),返回null。用法同removeFirst()
 *      pollFirst()         // 移除并返回首元素,如果集合为空(空元素)，就返回null。 其余用法同removeFirst()
 *      pollLast()          //移除并返回尾元素,如果集合为空(空元素)，就返回null。其余用法同removeLast()
 *
 *      其余的查询API
 *
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 15:37
 */
public class TestList {

    @Test
    public void test1(){
        List list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        System.out.println(list);
        list.add(1,"mzp");
        System.out.println("在指定位置添加元素："+list);

        List list2 = new ArrayList<>();
        list2.add("ee");
        list2.add("ff");
        /**
         * add()  添加集合时，会把元素整体作为一个新的元素，放入集合中
         */
        list.add(0,list2);
        System.out.println(list);
        /**
         * addAll()  添加集合时，会把集合中的元素一个一个的取出来，再放入新集合中
         */
        list.addAll(0,list2);
        System.out.println(list);
    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("bb");
        list.add("ff");
        list.add("oo");
        list.add("dd");
        System.out.println(list);

        Object obj = list.get(1);
        System.out.println("根据索引获取元素："+obj);

        int index = list.indexOf("bb");
        System.out.println("获取指定元素第一次出现的位置的索引值："+index);
        int index2 = list.lastIndexOf("bb");
        System.out.println("获取指定元素最后一次出现的位置的索引值："+index2);

        list.set(2,"mzp");
        System.out.println("修改索引为2的元素为mzp:"+list);

        /**
         * subList(start ,end)   前开后闭区间。返回一个新的list
         * 根据索引截取一个子list： 包含头，不包含尾。 [ )  前开后闭区间。
         */
        List subList = list.subList(1,3);
        System.out.println(subList);
    }

    @Test
    public void test3(){
        LinkedList list = new LinkedList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        System.out.println(list);

        System.out.println("list.get(index):"+list.get(1));

        list.addFirst("mzpFirst");
        list.addLast("mzpLast");
        System.out.println("LinkedList前后个添加一个元素:"+list);

        System.out.println("LinkedList获取首元素:"+list.getFirst());
        System.out.println("LinkedList获取尾元素:"+list.getLast());


        Object firstElement = list.removeFirst();   // 移除并获取首元素
        Object lastElement =  list.removeLast();    // 移除并获取尾元素
        System.out.println("首元素："+firstElement);
        System.out.println("尾元素："+lastElement);
        System.out.println("linkedList此时的元素："+list);

        // 重新恢复一下集合的元素
        list.addFirst("mzpFirst");
        list.addLast("mzpLast");
        /**
         * 这里移除的时候要注意。移除一个元素之后，size就会变化减小1。
         *      用removeFirst/removeLast做遍历移除元素的时候，要先把最初的size保存下来。 否则会导致一部分数据没有remove
         */
        int size = list.size();
        for(int i=0;i<size;i++){
            Object object = list.removeFirst();
//            Object object = list.removeLast();
            System.out.println(object);
        }

    }

}
