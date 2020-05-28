package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/22 17:07
 * @File : TestItreator
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 1 集合的遍历
 *      1.1 增强for循环
 *          for(被遍历的集合中的元素类型  变量名 : 被遍历的集合)
 *          {   ......   }
 *      1.2 Iterator迭代器
 *          迭代器都有一个类似指针的东西，来标记位置。初始位置在最前面（没有元素的地方。iterator.next()指针往后挪一位，并且取到这一位的元素）
 *          注意：迭代器移除元素 remove()， 此时被迭代的集合也会受影响，移除该元素。
 * 一、增强for循环
 * 二、Iterator迭代器
 *      iterator.hasNext()。  如果迭代具有更多元素，则返回true 。 （换句话说，如果next()返回一个元素而不是抛出异常，则返回true ）
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/22 17:07
 */
public class TestIterator {

    /**
     * 一、增强for循环
     */
    @Test
    public void test(){
        System.out.println("--------------增强for循环遍历List---------------------");
        ArrayList arrayList = new ArrayList();
        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("123");
        arrayList.add("456");
        arrayList.add("cc");
        for(Object str : arrayList){
            System.out.println(str);
        }

        System.out.println("--------------增强for循环遍历Set---------------------");
        Set set = new HashSet();
        set.add("set1");
        set.add("set2");
        set.add(999);
        for (Object obj : set){
            System.out.println(obj);
        }
    }

    /**
     * 二、Iterator迭代器
     */
    @Test
    public void test1(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("123");
        arrayList.add("456");
        arrayList.add("cc");
        System.out.println("原始的List："+arrayList);
        /**
         * 获取当前集合的迭代器
         */
        Iterator iterator = arrayList.iterator();
        /**
         * iterator.hasNext()
         *  如果迭代具有更多元素，则返回true 。 （换句话说，如果next()返回一个元素而不是抛出异常，则返回true ）
         */
        while (iterator.hasNext()){
            Object object = iterator.next();
            System.out.println(object);
            if("aa".equals(object)){
                iterator.remove();
            }
        }
        /**
         * 迭代器移除元素。 被迭代的集合会受影响
         */
        System.out.println("迭代器移除“aa”，List会受影响吗？ "+arrayList);

    }
}
