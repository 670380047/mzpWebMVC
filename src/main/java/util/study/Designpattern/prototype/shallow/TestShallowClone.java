package util.study.Designpattern.prototype.shallow;

import util.study.Designpattern.prototype.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 【原型模式】。 浅拷贝
 *  被克隆的对象需要实现Cloneable接口。（这是一个标记类型的接口，并没有方法，只是标记“这个类要做克隆”）
 *  以系统中已经存在在的一个对象为模型，然后（一般都是）基于内存的二进制流，来复制出来一个新对象。
 *      主要是通过克隆来复制一个新的一模一样的对象。
 *      而不是new一个对象，然后一个一个复制对象的值
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 18:17
 * @File : TestShallowClone
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestShallowClone {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("mzp");
        person.setAge(18);
        person.setScore(100);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("游戏");
        hobbies.add("音乐");
        person.setHobbies(hobbies);
        System.out.println("原型对象："+person);


        // 浅克隆得到的对象
        Person person1 = null;
        try {
            //浅拷贝出一个对象person1
            person1 = person.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("克隆对象："+person1);
        System.out.println("=======================查看原型和克隆是否是同一个对象？=======================");
        //不相等。说明是重新创建了一个对象。（但是对象里面的属性是否重新创建了呢？ 基本类型重新创建；至于引用类型：浅拷贝没有，深拷贝重新创建）
        System.out.println("原型对象==克隆对象？"+(person==person1));


        System.out.println("=======================查看原型和克隆中的那个“引用类型”list域是否是同一个对象？=======================");
        //相等。说明是浅拷贝。因为list属性并没有被复制一份新的出来，而是两个对象的list属性同时指向了同一个（引用）
        System.out.println("原型对象的引用(list)==克隆对象的引用？"+(person.getHobbies()==person1.getHobbies()));
        System.out.println("原型对象中的包装类型(Integer)==克隆对象的Integer？"+(person.getScore()==person1.getScore()));
        System.out.println("原型对象中的String类型==克隆对象的String？"+(person.getName()==person1.getName()));

        System.out.println("=======================查看原型和克隆中的那个“引用类型”list域是否同时被修改了？=======================");
        //给克隆对象的引用属性list添加一个新的元素：“浅拷贝”
        person1.getHobbies().add("浅拷贝");
        person1.setScore(998);
        person1.setName("张三");
        /**
         * 打印看原型对象和克隆对象有没有变化。
         *      发现原型对象也被改变了。说明list是两者共有的一个引用地址。而不是每个对象都有一个
         */
        System.out.println("原型对象："+person);     // 被修改了。person和person1里面的list一样
        System.out.println("克隆对象："+person1);    // 被修改了。person和person1里面的list一样
    }
}
