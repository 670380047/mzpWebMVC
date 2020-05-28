package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/28 10:19
 * @File : MyGeneric
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类：在类名后加上<T>, 不确定是什么类型，就用T做通配符
 *      <T> 中类型的字母可以随便写,但习惯上  T：Type   E：Element   K: Key   V:Value  R:Return
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/28 10:19
 */
public class MyGeneric<T> {
    private T value;

    private List<T> list = new ArrayList<>();

    public void addList(T t){
        this.value = t;
        this.list.add(this.value);
    }

    public T getValue(){
        return value;
    }

    public List<T> getList(){
        return this.list;
    }

    public void update(int id,T t){
        // ...
    }
}
