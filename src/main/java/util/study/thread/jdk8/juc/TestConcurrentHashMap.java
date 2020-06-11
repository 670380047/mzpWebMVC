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
 * 同步容器类：ConcurrentHashMap. 采用锁分段机制（jdk5）。
 *    1. concurrentLevel: 分段级别，默认是16. 就是分了16段。每段都是独立的锁。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 13:03
 */
public class TestConcurrentHashMap {
}
