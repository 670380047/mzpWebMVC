package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/24 15:08
 * @File : TestMap
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * java.util.Map: 用于存储成对对象的集合 key--value。   一个key对应一个value， 并且key不允许重复
 *      1 HashMap: 是Map接口的典型实现类。
 *              因为key的存储同set一样，使用hash算法，api中的原话：要成功存储和检索哈希表中的对象，用作键的对象必须实现hashCode方法和equals方法。
 *              HashMap保证key不允许重复的依据同HashSet一样（HashSet底层就是用HashMap存储的，Set的值就是key（set的值就是不重复的），value是一个空的Object对象）。
 *              原理：每个key生成的时候，都会根据他的内容生成一个对应的hashCode（哈希码，是一个int类型：32位二进制数））,可以理解为内存的地址（但他并不是内存的地址,是经过地址计算的一个整数
 *  *           对象类型时是调用Object的方法HashCode本地方法、数据类型是就是数值、String类型是：hashCode = 31*hashCode + char[i] 来计算的）。
 *
 *              然后采用hash算法来计算hash值： 用hashCode的值 “异或”上  hashCode的值“无符号右移”16位得到的值。 即 hash值= hashCode ^ (hashCode >>> 16)
 *              这个hash值是用用来计算元素将要放在哪个桶中（数组的下标）。
 *
 *              jdk7之前是取模运算：（hash值 % 数组大小n）来计算数组的下标， jdk7改用：(数组大小n - 1) & hash值。位运算相比除法取模运算，效率高得多。即用了“与运算”来计算桶的位置（数组的下标）。
 *              （注： hash值 % n  = (n-1) & hash值  ，是当n是2的指数的时候成立，比如n=2、4、6、8、16.... 所以hashMap扩容的时候，都是2的整数次幂，极保证条件成立，又有助于散列均匀）
 *
 *              此时得到了新元素将要放入的桶（数组）：
 *              如果当前桶（数组）是空的话，就直接把新元素放进去即可，返回null。 如果当前桶不为空，就根据key值来调用equals方法来遍历同种的链表（key对应的类型需要重写equals方法）。如果key值已存在，
 *              那就直接覆盖掉旧的value,并返回旧的value。如果key不存在，那就把新的键值对追加在链表的末尾。
 *              （jdk8中）如果链表的长度大于8，那么就链表转化红黑树来处理。红黑树的查询时间复杂度为 O(log n)
 *

 *              api中的原话：要成功存储和检索哈希表中的对象，用作键的对象必须实现hashCode方法和equals方法。
 *              To successfully store and retrieve objects from a hashtable(HashMap), the objects used as keys must implement the hashCode method and the equals method.
 *          1.1 LinkedHashMap: 是HashMap的子类。比HashMap多了一个链表来维护相邻元素的前后关系（便于遍历）。
 *                  1.1.1 LinkedHashMap在存储位置上同样是无序的，同样采用hash表。 只是多了一个链表来维护前后关系，所以给人的感觉是有序的
 *                  1.1.2 效率对比： HashMap 增、删等修改操作效率高,只需要判断HashCode和equals方法即可。
 *  *                        LinkedHashMap遍历效率高，因为有链表方便查找相邻元素。 增删操作效率相对低，因为多了对链表的处理。
 *      2 HashTable: 他是锁了哈希表，任何操作进来都是串行的，效率很低。
 *                  目前基本不用了。之所以存在，因为他还有一个子类Properties
 *                  保证线程安全可以使用ConcurrentHashMap,采用的是锁分段机制。--详见TestConcurrentHashMap类
 *              2.1 与HashMap相比：
 *                  2.1.1 HashTable是线程安全的,效率低。
 *                  2.1.2 HashTable不允许使用null作为key或者value  （HashMap允许key或value为null）
 *              2.3 Properties: 是HashTable的子类。  他的key和value都是String类型的： username=root
 *      3 TreeMap: 类比TreeSet,可以对key定义排序规则。 key只能存相同类型的数据。不能既有String又有Integer
 *              3.1 完整排序（Comparable接口）
 *                  3.1.1 添加到TreeSet中的元素需要实现Comparable接口的 compareTo(Object o)方法
 *              3.2 定制排序（Comparator接口）
 *                  3.2.1 声明一个类（MyComparator），实现Comparator接口的compare(Object o1,Object o2)方法
 *  *               3.2.2 将该类（MyComparator）的实例作为参数，传递给TreeMap的构造器
 *
 *
 *  一、HashMap常用操作
 *      Object put(Object key, Object value)
 *      Object remove(Object key)
 *      void putAll(Map t)
 *      void clear()
 *
 *  二、HashMap元素查询的操作:
 *       Object get(Object key) :根据key获取对应的value
 *       boolean containsKey(Object key)
 *       boolean containsValue(Object value)
 *       int size()
 *       boolean isEmpty()
 *       boolean equals(Object obj) :若两个Map中的内容模一样 返回true
 *
 *   三、map的遍历
 *       1. 方式一：获取map中所有的key---->  keySet()    然后用key取value
 *       2. 方式二：获取map中所有的value---> values()      注意：无法用value反取key
 *       3. 方式三：获取map中的内部类entry----> entrySet()   每个entry都是一个key-value
 *
 *   四、Properties
 *   五、TreeMap 自然排序（Comparable接口）
 *   六、TreeMap 定制排序（Comparator接口）
 *   七、测试自定义的缓存类。带有过期时间
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/24 15:08
 */
public class TestMap {

    /**
     *  一、HashMap常用操作
     *      Object put(Object key, Object value)
     *      Object remove(Object key)
     *      void putAll(Map t)
     *      void clear()
     */
    @Test
    public  void test1(){
        HashMap map = new HashMap();
        Person person1 = new Person("张三",18);
        Person person2 = new Person("张三",18);

        map.put(person1,18);    // 这一条记录被覆盖
        map.put(person2,19);    // 因为person类我已经重写了hashCode方法和equals方法。 所以只要内容相同（都是“张三”，18岁）就会被判定是重复的key
        System.out.println(map);


        Map map2 = new HashMap<>();
        map2.put("key1","v1");
        map2.put(1,2);
        map2.put(true,true);
        System.out.println(map2);

        map2.remove(1);
        System.out.println("移除key=1的数据："+map2);

        map2.putAll(map);
        System.out.println("putAll方法:"+map2);
    }

    /**
     * 二、HashMap元素查询的操作:
     *  Object get(Object key) :根据key获取对应的value
     *  boolean containsKey(Object key)
     *  boolean containsValue(Object value)
     *  int size()
     *  boolean isEmpty()
     *  boolean equals(Object obj) :若两个Map中的内容模一样 返回true
     */
    @Test
    public  void test2(){
        Person person = new Person("张三",18);

        Map map = new HashMap<>();
        map.put(person,18);
        map.put("key1","v1");
        map.put(1,2);
        map.put(true,true);
        map.put(new Person("张三",18),"放进去了" );   // 这个会覆盖掉之前key为new Person("张三",18)的键值对.因为Person重写了hashCode和equals方法。所以只要内容相同，在map中就被认为是同一个对象。
        System.out.println(map);
    // 因为Person重写了hashCode和equals方法。所以只要内容相同，在map中就被认为是同一个对象。
        boolean bool =  map.containsKey(new Person("张三",18));     // true
        System.out.println("containsKey测试：是否包含张三:"+bool);

        boolean bool2 = map.containsValue("v1");
        System.out.println("containsValue测试：是否包含‘v1’:"+bool2);

        int size = map.size();
        System.out.println("size测试：map的大小为："+size);


        Map map2 = new HashMap<>();
        map2.put(person,18);
        map2.put("key1","v1");
        map2.put(1,2);
        map2.put(true,true);

        boolean bool1 = map.equals(map2);
        System.out.println("equals测试："+bool1);

        boolean bool3 = map.isEmpty();
        System.out.println("isEmpty测试：是否为空："+bool3);
        map.clear();
        boolean bool4 = map.isEmpty();
        System.out.println("clear之后，再isEmpty测试：是否为空："+bool4);
        boolean bool5 =  map == null;
        System.out.println("clear之后:map是否为null："+bool5);
    }

    /**
     * 三、map的遍历
     *      1. 方式一：获取map中所有的key---->  keySet()    然后用key取value
     *      2. 方式二：获取map中所有的value---> values()      注意：无法用value反取key
     *      3. 方式三：获取map中的内部类entry----> entrySet()   每个entry都是一个key-value
     */
    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("张三",18);
        map.put("李四",19);
        map.put("王五",20);
        map.put("赵六",21);
        map.put(1,"Integer");


        /**
         * keySet()
         * 取出map中所有的key，返回一个Set
         */
        Set keys = map.keySet();

        System.out.println("增强for循环遍历");
        for(Object key : keys){
            Object value = map.get(key);
            System.out.print(value+" ");
        }

        System.out.println("\n====================================================");
        System.out.println("keySet()放发获取的set:"+keys);
        System.out.println("遍历map中的key:");
        keys.stream().forEach(System.out::println);
        System.out.println("====================================================");
        System.out.println("lambda表达式用map方法，根据key来获取对应的value：");
        keys.stream().map(k-> map.get(k)).forEach(v -> System.out.print(v+" "));


        /**
         *  values()
         *  返回的是map中所有的value。返回一个Collection
         *  可以用迭代器Iterator遍历
         */
        Collection collection = map.values();
        Iterator iterator = collection.iterator();
        System.out.println("\n===================迭代器遍历map的values=========================");
        while(iterator.hasNext()){
            Object value = iterator.next();
            System.out.print(value+" ");
        }
//        collection.stream().forEach(e-> System.out.println(e));

        /**
         * entrySet()
         * 获取map中的entry,entry是map的一个内部类。 返回一个Set
         * 一个entry对应一个key--value。
         */
        Set entrySet = map.entrySet();
        System.out.println("\n=============增强for循环遍历entrySet==========================");
        for(Object obj : entrySet){
            Map.Entry entry = (Map.Entry) obj;  // Entry是Map的一个内部类
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println( "key="+key+"  value="+value);
        }

        System.out.println("=============迭代器遍历entrySet==========================");
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Map.Entry entry = (Map.Entry) iterator1.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println( "key="+key+"  value="+value);
        }
    }

    @Test
    public void test4(){
        HashMap hashMap = new HashMap();
        hashMap.put("张三",18);
        hashMap.put("李四",19);
        hashMap.put("王五",20);
        hashMap.put("赵六",21);
        hashMap.put(1,"Integer");
        System.out.println("HashMap:按照插入循序遍历的吗？"+hashMap);

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("张三",18);
        linkedHashMap.put("李四",19);
        linkedHashMap.put("王五",20);
        linkedHashMap.put("赵六",21);
        linkedHashMap.put(1,"Integer");
        System.out.println("LinkedHashMap:按照插入循序变里的吗？"+linkedHashMap);
    }

    /**
     * 四、Properties
     */
    @Test
    public void test5(){
        /**
         * 1.创建Properties对象
         */
        Properties properties = new Properties();

        /**
         * 2.通过load()方法加载属性文件。 入参是一个节点流：字节输入节点流
         */
        try {
            properties.load(new FileInputStream("src/main/resource/TestJdbc.properties"));

            /**
             * 3. 通过 getProperty(String key) 通过key来获取对应的value值
             */
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            System.out.println("username="+username+"\npassword="+password);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 五、TreeMap 自然排序（Comparable接口）
     */
    @Test
    public void test6(){
        TreeMap treeMap = new TreeMap();
        treeMap.put(5,55);
        treeMap.put(4,44);
        treeMap.put(6,66);

//        treeMap.put("123","123");   // 会报错。 因为前面存了integet类型，这里就不能再存其他类型了
        System.out.println(treeMap);

        Person person1 = new Person("张三",18);       // 因为Person类实现了Comparable接口的 compareTo(Object o)方法，所以可以存入TreeSet
        Person person2 = new Person("李四",20);
        Person person3 = new Person("王五",19);
        TreeMap treeMap1 = new TreeMap();
        treeMap1.put(person1,"张三-18");
        treeMap1.put(person2,"李四-20");
        treeMap1.put(person3,"王五-19");
        System.out.println(treeMap1);
    }

    /**
     * 六、TreeMap 定制排序（Comparator接口）
     */
    @Test
    public void test(){
        Person person1 = new Person("张三",18);
        Person person2 = new Person("李四",20);
        Person person3 = new Person("王五",19);
        TreeMap treeMap1 = new TreeMap(new MyComparator()); // 使用定制排序MyComparator。自定义比较器，实现了Comparator接口的compare方法
        treeMap1.put(person1,"张三-18");
        treeMap1.put(person2,"李四-20");
        treeMap1.put(person3,"王五-19");
        System.out.println(treeMap1);
    }

    /**
     * 七、测试自定义的缓存类。带有过期时间
     */
    @Test
    public void test7(){
        /**
         * 定义带有过期时间的构造器。 过期时间为5000毫秒
         */
//        ExpiryMap<String,Integer> expiryMap = new ExpiryMap<>(5000);
//        expiryMap.put("first",1);
//        expiryMap.put("second",2);
//        expiryMap.put("third",3);
        /**
         * 使用默认的容量大小16， 和加载因子 0.75. 默认过期时间是三分钟：EXPIRY = 1000 * 60 * 3;
         */
        ExpiryMap<String,Integer> expiryMap = new ExpiryMap<>();
        expiryMap.put("first",1,10000);
        expiryMap.put("second",2,5000);
        expiryMap.put("third",3,15000);

        while(!expiryMap.isEmpty()){
//           Integer first =  expiryMap.get("first");
//           Integer second =  expiryMap.get("second");
//           Integer third =  expiryMap.get("third");
            System.out.println(expiryMap);
//            System.out.println("移除元素："+expiryMap.remove("third"));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
