package util.study.spiTest;

import util.study.spiTest.service.Animal;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPI步骤二: 使用ServiceLoader来加载配置文件中的实现类。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/10/11 10:45
 * @File : SPITest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SPITest {
    public static void main(String[] args) {
        // 使用ServiceLoader来加载指定的接口，他会去读取资源文件下面的接口文件
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        Iterator<Animal> iterator = animals.iterator();
        // 第一次遍历迭代器。在for循环（迭代）的时候开始去加载配置文件
        while (iterator.hasNext()) {
            // 在next()的时候，去利用反射实例化对象
            iterator.next().shout();
        }

        // 第二次再遍历迭代器时，走的就是缓存。即使是新创建的迭代器，也是同一个缓存，因为迭代器是个ServiceLoader的内部类，
        //      里面每次创建迭代器时生成的缓存都是根据ServiceLoader的成员变量providers这个linkedHashMap的value值来生成的缓存，因此是共享的。
        Iterator<Animal> iterator1 = animals.iterator();
        // 在for循环（迭代）的时候开始去加载配置文件
        while (iterator1.hasNext()) {
            // 在next()的时候，去利用反射实例化对象
            iterator1.next().shout();
        }

        // for循环也是一样的迭代
//        for (Animal animal : animals) {
//            // 调用接口的实现类的方法
//            animal.shout();
//        }
    }
}
