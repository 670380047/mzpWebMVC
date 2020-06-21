package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/20 17:07
 * @File : TestSet
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * * 集合框架
 *         java.util.Collection 接口：集合层次的根接口
 *              1.java.util.List 接口: 有序的，允许重复的。因为List体系的集合都有索引值
 *                  1.1 java.util.ArrayList：采用“数组”结构存储数据
 *                        优点：查询效率高，因为有索引值
 *                        缺点：增加和删除效率低，因为要前后移动元素
 *                  1.2 java.util.LinkedList：采用“链表”结构存储数据（双向链表）。链表结构用前后节点索引来连接各个元素
 *                        优点：增加和删除效率高，只需要维护前后节点就好了
 *                        缺点：查询效率低，要遍历
 *                  1.3 java.util.Vector(不常用)
 *              2.java.util.Set 接口: 存储无序，不允许重复。  存储使用的是hash表，每个hash码位置固定，元素的根据自己的hashCode不同，来存入不同的位置。
 *                  2.1 java.util.HashSet: 是Set接口的典型实现类。
 *                          2.1.1判断元素是否存在的依据：先根据hashCode(哈希码)判断，再根据equals()方法判断内容。
 *                              即：如果使用hashCode的时候，对象要重写hashCode()方法可以保证相同内容的对象，产生hashCode是相同的。 否则，不同对象的hashCode就不同。
 *                                  再重写 equals方法的话（请参照util.study.collection.Person类），可以保证相同内容的对象是相等的。 否则，内容相同，不保证元素相同
 *                                  （根据需要，选择是否重写这两个方法）
 *                          2.1.2使用Hash算法：每个对象生成的时候，jvm都会根据他的内容值来给他赋了一个hashCode（哈希码，是一个int类型：32位二进制数），可以理解为内存的地址（但他并不是内存的地址,是经过地址计算的一个整数
 *                                  对象类型时是调用Object的方法HashCode本地方法、数据类型是就是数值、String类型是：hashCode = 31*hashCode + char[i] 来计算的）。
 *
 *                                  然后根据hash算法计算hash值：用每个hashCode “异或”上 这个hashCode无符号右移（>>>）16位（高16位补0）后的数，得出来的那个值，hash值。即：hash值 = hashCode ^ (hashCode >>> 16)。
 *                                  这个值接下来是用来计算桶的的位置（计算数组下标）。(因为任意数异或（对位相反为1，相同为0）上0之后，都不变，所以实际就是hashCode的高16位和低16位异或了一下，用来减少散列的冲突)
 *
 *                                  jdk7之前是用取模运算（hash值 % 数组大小n），jdk7改为:（n-1）& hash值  ，即用了“与运算”来计算桶的位置。
 *                                  （注： hash%n = (n-1)&hash   ，是当n是2的指数的时候成立，比如n=2、4、6、8、16.... ）
 *
 *                                  得到桶的位置之后，也就是找到了当前这个hash值应该要放进哪个数组里面了。
 *                                  如果当前通为空的话，就直接把新元素放进去，作为桶（数组的第一个元素）；如果当前桶不为空，那就遍历这个桶（数据）中的链表，根据所要放进去元素（key）的equals方法（其实就是hashMap的key的equals方法。
 *                                  需要重写equals方法），来判断是否已经有相同的元素存在了，如果有，就覆盖掉，并返回旧的value。如果没有，就把新元素追加在这个链表的末尾，并返回null。
 *                                  （jdk8中新添加了红黑树）如果链表的大小大于8，那就把这个桶（数据）对应的链表转换为红黑树类存储。红黑树的查询时间复杂度为 O(log n)。
 *
 *
 *                          注意：如果一个对象重写了hashCode方法的话，那么只要对象的内容一样，他们生成的hashCode就会一样，类似String,String就重写了hashCode()
 *                               如果此时又重写了equals方法，来保证只要内容一样，就表示相同对象的话，就能保证同一个内容相同的对象不会被重复放进HashSet中。
 *
 *                          2.1.3 java.util.LinkedHashSet: 是HashSet的子类。比hashSet多了一个链表来维护相邻元素的前后关系（便于遍历）。
 *                              2.1.3.1 LinkedHashSet在存储位置上同样是无序的，同样采用hash表。 只是多了一个链表来维护前后关系，所以给人的感觉是有序的
 *                              2.1.3.2: 效率对比： HashSet 取、增、删等修改操作效率高,只需要判断HashCode和equals方法即可。
 *                                             LinkedHashSet遍历效率高，因为有链表方便查找相邻元素。 增删操作效率相对低，因为多了对链表的处理。
 *                              2.1.3.3 没有自己的方法，全是继承父类的，用法和HashSet一模一样。
 *                  2.2 java.util.TreeSet: 拥有自己的排序方式。 存相同类型的数据。不能既有String又有Integer
 *                      2.2.1 完整排序（Comparable接口）
 *                          2.2.1.1 添加到TreeSet中的元素需要实现Comparable接口的 compareTo(Object o)方法
 *                      2.2.2 定制排序（Comparator接口）
 *                          2.2.2.1 声明一个类（MyComparator），实现Comparator接口的compare(Object o1,Object o2)方法
 *                          2.2.2.2 将该类（MyComparator）的实例作为参数，传递给TreeSet的构造器
 * 一、HashSet
 * 二、LinkedHashSet
 * 三、TreeSet 完整排序（Comparable接口）
 * 四、TreeSet 定制排序（Comparator接口）
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/20 17:07
 */
public class TestSet {

    /**
     * 一、HashSet
     */
    @Test
    public void test1(){
        HashSet hashSet = new HashSet();

        /**
         * 存储是无序的，和添加顺序无关。
         */
        hashSet.add("aa");
        hashSet.add("bb");
        hashSet.add("cc");
        hashSet.add("dd");
        hashSet.add("aa");
        hashSet.add("mzp");
        hashSet.add(new Person("张三",18));   //如果没重写，会重复出现，因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了）
        hashSet.add("aa");
        hashSet.add(new String("aa"));
        hashSet.add(new Person("张三",18));     //如果没重写，会重复出现，因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了）

        Person person = new Person("李四",20);
        Person person1 = new Person("李四",20);
        System.out.println("person（李四）的哈希码："+person.hashCode());
        // 此处添加失败，因为重写hashCode方法和equals方法后，hashSet认为他俩内容相同就是同一个对象了。
        System.out.println("person1（李四）的哈希码："+person1.hashCode());

        // 如果没重写，就会重复出现。因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了。这里Person已经重写了，所以不会重复出现）
        System.out.println("person(李四)添加成功了？："+hashSet.add(person));
        // 如果没重写，就会重复出现。因为不是同一个对象（重写hashCode方法和equals方法后，就不会重复出现了。这里Person已经重写了，所以不会重复出现）
        System.out.println("person1(李四)添加成功了？："+hashSet.add(person1));


        String str1 = new String("admin");
        String str2 = new String("admin");
        System.out.println("str1(admin)的哈希码："+str1.hashCode());
        // 此处添加失败，因为重写hashCode方法和equals方法后，hashSet认为他俩内容相同就是同一个对象了。
        System.out.println("str2(admin)的哈希码："+str2.hashCode());

        System.out.println("str1(admin)添加成功了？："+hashSet.add(str1));
        System.out.println("str2(admin)添加成功了？："+hashSet.add(str2));

        System.out.println(hashSet);
    }

    /**
     * 二、LinkedHashSet
     */
    @Test
    public void test2(){
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("aa");
        linkedHashSet.add("cc");
        linkedHashSet.add("bb");
        linkedHashSet.add("dd");
        linkedHashSet.add("123");
        linkedHashSet.add("cc");
        System.out.println(linkedHashSet);
    }

    /**
     * 三、TreeSet 完整排序（Comparable接口）
     * TreeSet中的元素自带排序，利用的是 Comparable接口的 compareTo(Object o)方法。如果返回值是0，就视为重复元素。
     * 所以添加到TreeSet中的元素需要实现 Comparable接口的 compareTo(Object o)方法
     */
    @Test
    public void test3(){
        TreeSet treeSet = new TreeSet();
        treeSet.add(11);
        treeSet.add(55);
        treeSet.add(11);
        treeSet.add(22);
        treeSet.add(33);
        treeSet.add(11);
        System.out.println(treeSet);


        TreeSet treeSetPerson = new TreeSet();
        Person person = new Person("张三",18);
        Person person1 = new Person("李四",20);
        Person person2 = new Person("王五",19);
        treeSetPerson.add(person);      // 因为Person类实现了Comparable接口的 compareTo(Object o)方法，所以可以存入TreeSet
        treeSetPerson.add(person1);
        treeSetPerson.add(person2);
        System.out.println(treeSetPerson);
    }

    /**
     * 四、TreeSet 定制排序（Comparator接口）
     */
    @Test
    public void test4(){
        TreeSet treeSet = new TreeSet(new MyComparator());  // 使用定制排序MyComparator。自定义比较器，实现了Comparator接口的compare方法
        Person person = new Person("张三",18);
        Person person1 = new Person("李四",20);
        Person person22 = new Person("王五",19);
        treeSet.add(person);
        treeSet.add(person1);
        treeSet.add(person22);
        System.out.println("自定义比较器："+treeSet);


        /**
         * InnerComparator是定义在当前类TestSet中的又一个类.
         * 在本类中可以直接 new InnerComparator()
         * 在其他类中时，可以定义成静态的，然后需要  外部类.内部类 的方式： TestSet.InnerComparator()
          */
//        TreeSet treeSetInner = new TreeSet(new InnerComparator());
        TreeSet treeSetInner = new TreeSet(new TestSet.InnerComparator());
        treeSetInner.add(person);
        treeSetInner.add(person1);
        System.out.println("内部类方式："+treeSetInner);


        /**
         * 匿名内部类方式：
         * 匿名内部类仅在此处有效
         */
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person  && o2 instanceof  Person){
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    if(p1.getAge().equals(p2.getAge())){
                        return p1.getName().compareTo(p2.getName());    // String 自己有重写compareTo方法
                    }else{
                        return p1.getAge().compareTo(p2.getAge());   // Integer 自己也有重写compareTo方法
                    }
                }
                return 0;
            }
        };
        TreeSet treeSetInnerAnonymous = new TreeSet(comparator);
        treeSetInnerAnonymous.add(person);
        treeSetInnerAnonymous.add(person1);
        System.out.println("匿名内部类方式："+treeSetInnerAnonymous);

        /**
         * 使用lambda表达式方式
         */
        TreeSet treeSetLambda = new TreeSet((o1,o2)->{
            if(o1 instanceof Person  && o2 instanceof  Person){
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if(p1.getAge().equals(p2.getAge())){
                    return p1.getName().compareTo(p2.getName());    // String 自己有重写compareTo方法
                }else{
                    return p1.getAge().compareTo(p2.getAge());   // Integer 自己也有重写compareTo方法
                }
            }
            return 0;
        });
        Person person2 = new Person("王五",22);
        Person person3 = new Person("赵六",16);
        treeSetLambda.add(person);
        treeSetLambda.add(person1);
        treeSetLambda.add(person2);
        treeSetLambda.add(person3);
        System.out.println("lambda表达式方式："+treeSetLambda);


    }



    /**
     * 内部类方式
     */
    public class InnerComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            if(o1 instanceof Person  && o2 instanceof  Person){
                Person p1 = (Person) o1;
                Person p2 = (Person) o2;
                if(p1.getAge().equals(p2.getAge())){
                    return p1.getName().compareTo(p2.getName());    // String 自己有重写compareTo方法
                }else{
                    return p1.getAge().compareTo(p2.getAge());   // Integer 自己也有重写compareTo方法
                }
            }
            return 0;
        }
    }
}
