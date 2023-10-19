package util.study.spiTest.impl;

import util.study.spiTest.service.Animal;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/10/11 10:37
 * @File : Cat
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Cat implements Animal {
    @Override
    public void shout() {
//        测试git的配置文件：git config --local user.name "毛宗鹏"
        System.out.println("猫的叫声：喵喵1...");
    }
}
