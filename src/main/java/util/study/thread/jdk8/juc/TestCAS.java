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
 *                2.CAS（Compare And Swap）算法保证数据的原子性
 *                    2.1 CAS算法是硬件对于并发操作共享数据的支持
 *                    2.2 CAS包含三个操作数：
 *                        内存值：V   （线程从主存中读出来的值）
 *                        预估值：A    (准备修改的时候，此时主存中的值。这个值可能是其他线程修改后的值，会造成V和A不相等。然后当前线程就会更新失败，什么也不做)
 *                        更新值：B    (当前线程的计算结果，准备更新会主存中去)
 *                        动作：当且仅当 V == A时， 就让V = B。 否则什么都不做
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
                     * 进行比较之前，先获取一下内存的值
                     */
                    int expectedValue = cas.get();

                    int B = (int) (Math.random()*101);  // 这个模拟线程最新的变更结果，想要写入主存的值
                    /**
                     * 往主存中写入是否成功
                     */
                    boolean bool =  cas.compareAndSet(expectedValue, B);
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
     * 1.获取 V
     * 获取内存的值
     * @return
     */
    public synchronized int get(){
        return value;
    }

    /**
     * 2.比较
     * 对比 V 和 A是否相等
     * @param expectedValue  预估值A，
     * @param newValue  更新值B
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        /**
         * 获取当前内存的值
         */
        int oldValue = value;
        if(oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    /**
     * 3.设置
     * 如果V==A, 就把B的值更新给V
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue );
    }
}