package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 16:13
 * @File : TestCAS
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 模拟 CAS算法
 *  二、 原子变量：jdk1.5之后，java.util.concurrent.atomic 包下提供了常用的原子变量.
 *            原子变量特性：
 *                1.变量都用 volatile保证内存可见性
 *                2.CAS（Compare And Swap）算法保证数据的原子性。
 *                      大致可分为两步：1.取值（这个是个同步方法）   2. 比较和替换 （这也是一个同步方法）
 *                    2.1 CAS算法是硬件对于并发操作共享数据的支持
 *                    2.2 CAS包含三个操作数：
 *                        内存值：V   （线程从主存中读出来的值）
 *                        预估值：A    (准备修改的时候，此时主存中的值。这个值可能是其他线程修改后的值，会造成V和A不相等。然后当前线程就会更新失败，什么也不做)
 *                        更新值：B    (当前线程的计算结果，准备更新会主存中去)
 *                        动作：当且仅当 V == A时， 就让V = B。 否则什么都不做
 *              CAS算法相比于锁：
*                     优点：效率高。因为他如果失败的话，他并不会阻塞（不会放弃CPU的执行权），他会继续去执行（这个需要我们自己去写处理失败之后，再次处理的逻辑）。
 *                    缺点：需要我们写的逻辑比较多。比如，处理失败之后，重新处理的逻辑
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/11 16:13
 */
public class TestCAS {
    public static void main(String[] args) {
        CompareAndSwap cas = new CompareAndSwap();
        for(int i=0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 进行比较之前，先获取一下内存的值: V
                     */
                    int expectedValue = cas.get();

                    int B = expectedValue + (int) (Math.random()*101);  // 这个模拟线程最新的变更结果，想要写入主存的值
                    /**
                     * 往主存中写入是否成功.
                     * 这里的第一个参数expectedValue可以理解为A了，就是期望值（期望内存中的值还是它，没有变化）。
                     */
                    boolean bool =  cas.compareAndSet(expectedValue, B);
                    /**
                     * .......这里可以写处理失败的逻辑。（让他继续再来一遍）
                     */
                    System.out.println(bool+"---期望值(A)："+expectedValue+"---最新值(B)："+B);
                }
            }).start();
        }
    }
}

class CompareAndSwap{
    /**
     * 内存值
     */
    private int value;

    /**
     * 1.获取 V（是一个同步方法）
     * 获取内存的值
     * @return
     */
    public synchronized int get(){
        return value;
    }

    /**
     * 2.比较和替换（是一个同步方法）
     * 对比 V 和 A是否相等
     * @param expectedValue  预估值A，
     * @param newValue  更新值B
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        /**
         * 2.1获取当前内存的值：V
         * oldValue是当前内存值V（有可能已经被其它线程改过了）
         * expectedValue是期望值A。（期望还是跟原来的内存值V一样，期望V没有变化）
         */
        int oldValue = value;
        if(oldValue == expectedValue){
            /**
             * 如果内存值V = 期望值A 。 就表明没有其他线程进来改变过，那就可以放心修改值了。让V = B
             */
            this.value = newValue;
        }
        return oldValue;
    }

    /**
     * 3.设置. 这里就是调用步骤2（同步方法。 如果第步骤2直接返回true或false，那么步骤3其实可以不要了。）
     * 如果V==A, 就把B的值更新给V
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        /**
         * 调用步骤2，如果成功更新，那么也就是V和A相等（这个方法返回的就值修改之前的V，不是更新后的B）
         * expectedValue（在这里可以叫A）的值也是修改之前的V。 如果相等就是true，失败就是false。
         * 其实在步骤2里面的if条件中已经验证过了，这里只是一个返回值（结果）而已。也可以直接让步骤2返回true或false。
         */
        return expectedValue == compareAndSwap(expectedValue,newValue );
    }
}