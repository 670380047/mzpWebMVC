package util.study.spiTest.impl;

import util.study.spiTest.service.Animal;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/10/11 10:36
 * @File : Dog
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Dog implements Animal {
    @Override
    public void shout() {
        System.out.println("狗的叫声：汪汪1...");
    }
}
