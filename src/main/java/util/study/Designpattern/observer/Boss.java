package util.study.Designpattern.observer;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:17
 * @File : Boss
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:17
 */
public class Boss implements Person{

    String name ;
    List<Person> list = new ArrayList<>();

    public Boss() {
    }

    public Boss(String name) {
        this.name = name;
    }

    /**
     * 添加员工
     * @param person
     */
    public void addStaff(Person person){
        this.list.add(person);
    }

    /**
     * 批量给员工推送消息
     * @param message
     */
    public void sendMessage(String message){
        Iterator<? extends Person> iterator = this.list.iterator();
        while(iterator.hasNext()){
            iterator.next().getMessage(message);
        }

    }

    @Override
    public void getMessage(String message) {
        System.out.println(name+"收到消息："+message);
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }


}
