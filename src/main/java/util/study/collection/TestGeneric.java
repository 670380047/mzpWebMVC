package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/27 18:50
 * @File : TestGeneric
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 1.为什么使用泛型:
 *      1.1 应用场景-->某个类的处理逻辑对许多对象都适用时（如dog类，cat类），可以把这个里设置为泛型，存哪个对象都是一样处理逻辑。
 *              这个时候我们在定义类的时候，不具体指定dog还是cat，而是定义一个 <T>来做通配符。
 *      1.2 使用泛型优点：可以限定存入集合/Map中的类型，当取出来用的时候不用强转（强转时容易发生异常）。
 *      1.3 不使用泛型缺点：如果不使用泛型，意味着集合中就可以存任意类型的数据，
 *                  此时若需要具体使用到某一类型时，就需要做类型转换，可能会引发类型转换异常:ClassCastException
 *  2.泛型： 在java中以 "< >"形式呈现，尖括号中只能存放引用类型（各种对象之的都是）如： Integer,不能存放int。
 *          用于限制集合中存放的数据类型
 *  3. 泛型在集合中的应用
 *  4. 自定义泛型类、接口、方法
 *  5. 通配符： ？  是一个问号，可以接受任意类型的集合。（单独使用通配符的很少）
 *          5.1 为什么使用通配符：虽然Person是Man的父类，但是List<Person>不是List<Man>的父类。
 *          5.2  List<? extend Person> :（使用的很多） 可以传递Person 本类泛型的集合 以及 Person子类泛型的集合。可以使用多态的特性，使代码更灵活
 *          5.3  List<? super Person> : 可以传递Person 本类泛型的集合  以及 Person父类泛型的集合
 *
 * 一、不使用泛型的缺点
 *      使用某个具体类型时，需要类型转换
 * 二、在集合/Map中应用泛型
 * 三、使用自定义泛型类、接口
 * 四、自定义泛型方法
 * 五、通配符： ？  是一个问号，可以接受任意类型的集合  （单独一个？ ，不常用，相当于不使用泛型）
 *      取出数据后，还要类型转换
 * 六、List<? extend Person>  可以使用多态的特性
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/27 18:50
 */
public class TestGeneric {

    /**
     * 一、不使用泛型的缺点
     *     使用某个具体类型时，需要类型转换
     */
    @Test
    public void test1(){
        List list = new ArrayList<>();
        list.add("aa");
        list.add("cc");
        list.add("bb");
        list.add("dd");
        list.add("ee");
        list.add("aa");
//        list.add(11);  // 下面类型转换，转换成String时会报错。ClassCastException: java.lang.Integer cannot be cast to java.lang.String

        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            /**
             * 需要类型转换。
             */
            Object object = iterator.next();
            String str = (String)object;
            System.out.println(str.toUpperCase());
        }
    }

    /**
     * 二、在集合/Map中应用泛型
     */
    @Test
    public void test2(){
        Map<String,Integer> map = new HashMap<>();
        map.put("first",1);
        map.put("second",2);
        map.put("third",3);
        System.out.println(map);

        System.out.println("\n======================遍历map中的key，通过key获取value===========================");
        /**
         * 获取map中所有的key
         */
         Set<String> set = map.keySet();
         for(String s : set){
             System.out.print(" key = "+s+",value = "+map.get(s));
         }

        System.out.println("\n======================遍历map中的value===========================");
        /**
         * 获取map中所有的value
         */
         Collection<Integer> coll = map.values();
         Iterator<Integer> iterator = coll.iterator();
         while(iterator.hasNext()){
            Integer i = iterator.next();
             System.out.print(" value = "+i);
         }

        System.out.println("\n======================通过获取entry，来遍历map中的key-value===========================");
         Set<Map.Entry<String,Integer>> entries = map.entrySet();
         for(Map.Entry<String,Integer> entry : entries){
             String key = entry.getKey();
             Integer value = entry.getValue();
             System.out.print(" key = "+key+",value = "+value);
         }
    }

    /**
     * 三、使用自定义泛型类、接口
     */
    @Test
    public void test3(){
        MyGeneric<Person> myGeneric = new MyGeneric<>();
        Person person = new Person("张三",18);

        myGeneric.addList(person);
        System.out.println(myGeneric.getList());
    }

    /**
     * 四、自定义泛型方法
     */
    @Test
    public void test4(){
        String str = "自定义泛型方法。";
        System.out.println(str);
        str = test4(str);
        System.out.println(str);
    }

    /** 四、自定义泛型方法
     * 在返回值前面加了个 <M> ,表示可以声明一个 M类型的泛型。 和类名上面的<T>的作用是一样的（允许声明一个T类型的泛型）。
     * 在返回值前面声明类型，在入参传参时确定具体类型。
     *
     * 在泛型类中声明了一个泛型方法，使用泛型M，注意这个M是一种全新的类型;
     * 可以与泛型类中声明的T不是同一种类型。
     * 意义：调用传入M类型的参数（此时确定M的具体类型），这里的返回值也是M类型
     */
    public <M>  M test4(M t){
        /**
         * 因为不知道M是什么类型，所以下面的字符串要强转成M。
         * 因为传入的就是M类型，所以下面强转成M时，肯定不会出错。
         */
        t = (M) "调用时在入参处确定泛型的具体类型";
        return t;
    }

    /**
     * 五、通配符： ？  是一个问号，可以接受任意类型的集合  （单独一个？ ，不常用，相当于不使用泛型）
     *      取出数据后，还要类型转换
     */
    @Test
    public void test5(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",18));

        List<Man> manList = new ArrayList<>();
        manList.add(new Man("男人",18));
//        personList = manList;    // 这个会报错。虽然Person是Man的父类，但是List<Person>不是List<Man>的父类。无法使用多态
        /**
         * 定义了带有通配符的list：  List<?>
         */
        List<?> list = new ArrayList<>();
        list = manList;
        show(list);

        list = personList;
        show(list);

    }

    /**
     * 五、通配符： ？  是一个问号，可以接受任意类型的集合  （单独一个？ ，不常用，相当于不使用泛型）
     *      取出数据后，还要类型转换
     * @param list
     */
    public void show(List<?> list){
        if(list.size()>0){
           Object obj = list.get(0);

           if(obj instanceof Man){
               Man man = (Man) obj;
               man.eat();
               /**
                *  这里用else if很关键（类似switch）。匹配到一个就不太匹配其他的了。
                *  如果不用的else if的话（用if的话），他会两个都执行。
                *  因为Man也是Person的子类。
                *  所以如果Man对象进来的话，两个都能匹配到。
                 */
           }else if(obj instanceof Person){
               Person person = (Person) obj;
               person.eat();
           }
        }
    }

    /**
     * 六、List<? extend Person>  可以使用多态的特性
     */
    @Test
    public void test6(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",18));

        List<Man> manList = new ArrayList<>();
        manList.add(new Man("男人",18));

        List<? extends Person> list = new ArrayList<>();
        list = personList;
        show1(list);

        list = manList;
        show1(list);
    }

    /**
     * 六、List<? extend Person>  可以使用多态的特性
     */
    public void show1(List<? extends Person> list){
        Iterator<? extends Person> iterator = list.iterator();
        while (iterator.hasNext()){
            /**
             * 这里就可以使用多态了。
             *  1.如果是父类Person传进来，那就调用的是父类自己的eat()方法和run方法
             *  2.如果是子类Man传进来，那就是向上转型，调用父类中被子类重写的方法，如 eat()。
             *      如果没有重写，那就调用从父类继承过来的方法，如run()
             */
            Person person = iterator.next();
            person.eat();
            person.run();
        }
    }

}
