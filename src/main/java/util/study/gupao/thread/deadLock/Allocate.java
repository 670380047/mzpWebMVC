package util.study.gupao.thread.deadLock;

import java.util.ArrayList;
import java.util.List;

/**
 *  测试死锁。 ==》DeadLockDemo类中
 *      这是一个调配类，只有允许的类才能去进一步的申请synchronized的锁。
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/1 17:04
 * @File : Allocate
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Allocate {

    private List<Object> list = new ArrayList<>();

    /**
     * 判断集合中是否有对象，已经有的话就申请失败。这是个同步方法，锁监视器是对象
     * @param from
     * @param to
     * @return
     */
    synchronized  boolean apply(Object from ,Object to){
        if (list.contains(from)||list.contains(to)){
            return false;
        }
        list.add(from);
        list.add(to);
        return true;
    }

    /**
     * 操作完之后，释放集合中的元素。
     * @param from
     * @param to
     */
    synchronized void free(Object from, Object to) {
        list.remove(from);
        list.remove(to);
    }
}
