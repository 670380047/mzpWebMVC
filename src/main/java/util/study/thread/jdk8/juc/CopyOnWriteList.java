package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 14:10
 * @File : CopyOnWriteArrayList
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CopyOnWriteArrayList  写入并复制
 *
 * 一、这个是测试线程池的写法的、并且测试把List、Map、set转换为同步。与本类无关
 *      Collections工具类中的synchronizedList 、 synchronizedMep 、synchronizedSet等
 *      都可以把传入的List 、 Map 、Set中的方法加上一个synchronized关键字，来保证线程安全
 * 二、测试CopyOnWriteArrayList  写入并复制
 *      缺点：添加操作多时，效率非常低，因为每次添加都会进行复制，开销非常大。
 *      优点：并发迭代操作多时，可以使用。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 14:10
 */
public class CopyOnWriteList {

    public static void main(String[] args) {
        TestCopyOnWriteArrayListRun listRun = new TestCopyOnWriteArrayListRun();
        threadPool(listRun);


        /**
         * 1.创建一个runnable接口的实现了的实例
         */
//        HelloRun run = new HelloRun();
//        threadPool(run);
    }


    /**
     * 创建一个线程池。接收一个Runnable接口的实例
     * 1. 传入一个线程实例
     */
    public static void threadPool(Runnable run){

        /**
         * 2.创建一个指定大小的线程池
         */
        ExecutorService runTask = Executors.newFixedThreadPool(3);
        try {
            for(int i=0;i<3;i++){  // 采用3个线程，把这个任务跑了5次
                /**
                 * 提交线程池
                 */
                runTask.submit(run);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            runTask.shutdown();   //记得用完要关闭线程池
            if(runTask.isShutdown()){
                System.out.println("线程已关闭");
            }else{
                System.out.println("线程未关闭，请注意！！！！");
            }
        }
    }

}

/**
 * 一、这个是测试线程池的写法的。与本类无关
 */
class HelloRun implements Runnable{
    /**
     * Collections工具类中的synchronizedList 、 synchronizedMep 、synchronizedSet等
     * 都可以把传入的List 、 Map 、Set中的方法加上一个synchronized关键字，来保证线程安全。
     */
    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    static {
        list.add("AA");
        list.add("CC");
        list.add("BB");
        list.add("EE");
        list.add("DD");
    }

    @Override
    public void run() {
        Iterator it = list.iterator();
        while(it.hasNext()){
            System.out.println(Thread.currentThread().getName()+":"+it.next());
        }
    }
}

/**
 * 二、测试CopyOnWriteArrayList  写入并复制
 */
class TestCopyOnWriteArrayListRun implements Runnable{

    private static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("CC");
//        list.add("BB");
//        list.add("EE");
//        list.add("DD");
    }

    @Override
    public void run() {
        Iterator it = list.iterator();
        while(it.hasNext()){
            System.out.println(Thread.currentThread().getName()+":"+it.next());
            /**
             * 如果是普通的list的话，这里会引发“并发修改异常”
             * 就是在迭代器的操作过程中，又用list进行操作。操作的是同一个对象。
             *  以前的解决：要么用list操作，要么就迭代器操作。只能用一个。
             *
             *  现在的解决办法，把ArrayList换成 CopyOnWriteArrayList<>()实例 ,即“写入并复制”
             *      内存开销很大：因为每一次写入都会从新复制一份。
             *        (这个不准确)    比如这里：我有2个元素（AA  CC），每次遍历一个元素的时候，都会复制整个list（5个元素），并且让往里面添加一个XX元素
             *
             */
            list.add("XX");
        }
    }
}