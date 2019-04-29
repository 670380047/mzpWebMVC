package util.listtest;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/29 14:55
 * @File : ArrayAndLinked
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *测试ArrayList和LinkedList的插入速度。
 * 结论：头部插入：linkedList明显效率高于ArrayList（10万条有百倍的差距）。  尾部插入时，二者效率相差不大。
 * ArrayList的优势在于根据下标可以快速定位元素，进行修改。 但新增和删除效率比较低，因为要进行元素的移动
 * LinkedList的优势在于链表结构可以快速的进行插入和删除元素的操作。尤其是头部和尾部（java中Linked是双向链表）元素
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/29 14:55
 */
public class ArrayAndLinked {
    public static void main(String[] args) {
        List list;
        //实例化一个ArrayList
        list = new ArrayList();
        insertFirst(list,"ArrayList");

        //实例化一个LinkedList
        list = new LinkedList();
        insertFirst(list,"LinkedList");

        System.out.println("==============================分割线==========================================================");

        //实例化一个ArrayList
        list = new ArrayList();
        insertLast(list,"ArrayList");

        //实例化一个LinkedList
        list = new LinkedList();
        insertLast(list,"LinkedList");

        System.out.println("==============================分割线==========================================================");

        //实例化一个ArrayList
        list = new ArrayList();
        modify(list,"ArrayList");

        //实例化一个LinkedList
        list = new LinkedList();
        modify(list,"LinkedList");
    }

    /**
    * @Author maozp3
    * @Description: 在头部插入新元素
    * @Date: 15:00 2019/4/29
    * @Param [l, type]
    * @return void
    **/
    private static void insertFirst(List list, String type){
        //定义10W次插入
        int total = 100*1000;
        final int number = 7;
        //以当前时间作为开始时间
        Long start = System.currentTimeMillis();
        for(int i = 0; i < total; i++){
            list.add(0,number);
        }
        //结束时间
        Long end = System.currentTimeMillis();
        Long time = end-start;
        System.out.println("在"+type+"最  前面  插入"+total+"条数据，总共耗时"+time+"毫秒");
    }


    /**
    * @Author maozp3
    * @Description: 在尾部插入新元素
    * @Date: 15:18 2019/4/29
    * @Param [list, type]
    * @return void
    **/
    private static void insertLast(List list, String type){
        //定义10W次插入
        int total = 100*1000;
        final int number = 7;
        //以当前时间作为开始时间
        Long start = System.currentTimeMillis();
        for(int i = 0; i < total; i++){
            //每次都插入在list的最后一个位置处list.size
            list.add(list.size(),number);
        }
        //结束时间
        Long end = System.currentTimeMillis();
        Long time = end-start;
        System.out.println("在"+type+"最  后面  插入"+total+"条数据，总共耗时"+time+"毫秒");
    }

    private static void modify(List<Integer> list, String type){
        int total = 100*1000;
        int index = total/2;
        final int number = 7;
        //初始化
        for(int i=0;i<total;i++){
            list.add(number);
        }
        //开始时间
        Long start = System.currentTimeMillis();
        for( int i=0;i<total;i++){
            int n = list.get(index);
            n++;
            list.set(index,n);
        }
        Long end = System.currentTimeMillis();
        Long time = end-start;
        System.out.println(type+"总长度是"+total+",定位到第"+index+"元素，取出来+1，再放回去，重复了"+total+"次，共耗时"
        +time+"毫秒");
    }
}
