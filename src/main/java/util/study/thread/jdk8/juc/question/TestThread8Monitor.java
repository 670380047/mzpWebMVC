package util.study.thread.jdk8.juc.question;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/15 12:39
 * @File : TestThread8Monitor
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程8锁。
 *      1.关键点：非静态方法的锁为（默认）：this     （对象锁：锁住的是单个对象，必须拥有这个对象的锁，才能操作该对象的同步方法，拥有其他对象的锁就不行。）
 *              静态方法的锁为（默认）：类名.class   （类锁：锁住的是以类为单位的。必须拥有这个类的锁，才能操作这个类的同步块/方法。拥有对象锁就不行）
 *      2.关键点：在某一时刻，只能有一个线程获取到锁。
 *              对象锁和类锁都是一样的，一个线程获取了这把锁，其他线程就获取不到
 * 题目：判断打印“one” or "two"
 * 情况1：两个同步方法getOne()  getTwo()，然后启动两个线程分别调用他们。
 *      打印结果：one  two
 * 情况2：在情况1的条件下，在getOne里面添加了个Thread.sleep(3000)。
 *      打印结果：(等3秒) one two
 * 情况3：在情况2的条件下，新增了一个普通方法（不是同步方法）getThree()，然后启动一个线程来调用它。
 *      打印结果：three （等待3秒） one two
 * 情况4：在情况2（情况3注释掉）的条件下，new了一个number2的对象。 然后用number1去调用getNoe(),然后用number2去调用getTwo。
 *      打印结果：two （等待3秒）one
 * 情况5：在情况2条件下，把getOne()改为静态同步方法。两个线程中都用number1，一个去调用getOne(),一个去调用getTwo()。
 *      打印结果：two （等待3秒） one
 * 情况6：在情况2的条件下，把getOne()和getTwo()都改为静态方法。两个线程中都用number1，一个去调用getOne(),一个去调用getTwo()。
 *      打印结果：(等3秒) one two
 * 情况7：在情况2条件下，把getOne()改为静态同步方法，getTow()是非静态的同步方法(),然后用两个对象number1调用getOne().number2调用getTwo()方法。
 *      打印结果：two (等待3秒) one
 * 情况8：在情况2条件下，getOne()和getTow()均为静态同步方法。然后用两个对象number1调用getOne().number2调用getTwo()方法。
 *      打印结果：(等3秒) one two
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/15 12:39
 */
public class TestThread8Monitor {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number1.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                number1.getTwo();
                number2.getTwo();
            }
        }).start();

        /**
         * 情况3
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number1.getThree();
//            }
//        }).start();
    }
}

class Number{
    public static synchronized void getOne(){
        try {
            /**
             * 情况2.
             */
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public static synchronized void getTwo(){
        System.out.println("two");
    }

    /**
     * 情况3
     */
    public void getThree(){
        System.out.println("three");
    }
}
