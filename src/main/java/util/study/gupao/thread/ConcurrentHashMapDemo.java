package util.study.gupao.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap使用样例
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/11 14:30
 * @File : ConcurrentHashMapDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ConcurrentHashMapDemo {

    ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();


    public void test(){
        String name;
        int age;

        chm.put("姓名", "小毛");
        chm.put("年龄", 18);
        /**
         * CHM中的key 和 value 都不允许为null
         */
//        chm.put(null,null);
//        chm.put("test",null);

        name = chm.get("姓名").toString();
        age = Integer.parseInt(String.valueOf(chm.get("年龄")));

        System.out.println("姓名："+name+"；年龄："+age);

    }

    public static void main(String[] args) {
        ConcurrentHashMapDemo chmd = new ConcurrentHashMapDemo();
        chmd.test();

        System.out.println(Integer.toBinaryString(32795));
        int binary;
        System.out.println( binary = Integer.parseInt("7fffffff",16));
        System.out.println(Integer.toBinaryString(binary));
    }
}
