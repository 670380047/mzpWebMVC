package util.study.gupao.thread.ThreadLocal;

/**
 * ThreadLocal中ThreadLocalMap使用的hashCode。 魔数：HASH_INCREMENT = 0x61c88647
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/2 17:18
 * @File : ThreadLocalHashCodeDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ThreadLocalHashCodeDemo {

    /**
     * 斐波那契散列的魔数：0x61c88647
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    private static void magicHash(int size) {
        int hashCode = 0;
        /**
         * 这里的每一次循环都相当于是创建了一个ThreadLocal对象。
         */
        for (int i = 0; i < size; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            System.out.print( ((size-1)&hashCode)+"  "); //这里打印的就是每个ThreadLocal对象即将放入的那个桶的下标
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /**
         * 分别测试16个ThreadLocal对象和32个ThreadLocal对象，在map桶中的分布情况。
         */
        magicHash(16);  // 7  14  5  12  3  10  1  8  15  6  13  4  11  2  9  0
        magicHash(32);  // 7  14  21  28  3  10  17  24  31  6  13  20  27  2  9  16  23  30  5  12  19  26  1  8  15  22  29  4  11  18  25  0
    }
}
