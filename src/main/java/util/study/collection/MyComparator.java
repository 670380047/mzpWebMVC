package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/22 16:27
 * @File : MyComparator
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.Comparator;

/**
 * 自定义的比较器,实现Comparator接口的compare方法。 大于返回1   小于返回-1   等于返回0
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/22 16:27
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Person  && o2 instanceof  Person){
            Person p1 = (Person) o1;
            Person p2 = (Person) o2;
            if(p1.getAge().equals(p2.getAge())){
                return  p1.getName().compareTo(p2.getName());    // String 自己有重写compareTo方法
            }else{
                return  p1.getAge().compareTo(p2.getAge());   // Integer 自己也有重写compareTo方法
            }
        }
        return 0;
    }


}
