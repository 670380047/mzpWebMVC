package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 13:03
 * @File : TestConcurrentHashMap
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 同步容器类：ConcurrentHashMap.
 * 注意！！:jdk8把取消了ConcurrentHashMap锁分段机制，对他采用了CAS算法，称之为“无锁算法”，相对不锁来说，不存在阻塞的、上下文切换的问题
 *
 * 同步容器类：ConcurrentHashMap. 采用锁分段机制（jdk5）。 jdk8之前的map是：数组加链表的形式。 jdk8是数组+链表+红黑树
 *    1. concurrentLevel: 分段级别，默认是16. 就是分了16段。每段都是独立的锁。
 *    2. 16段，每段各有一个锁，只要访问的不是同一个数组（map底层是数组，每个数组上链接一个链表（jdk8中，当练链表大于8个时，把链表改为红黑树））
 *          就可以并发访问，互不干涉。如果是访问的同一个数组，比如下标都是2的那一块，那么就会竞争锁
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 13:03
 */
public class TestConcurrentHashMap {
}
