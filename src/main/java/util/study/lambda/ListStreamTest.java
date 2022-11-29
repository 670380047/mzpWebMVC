package util.study.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/9 17:36
 * @File : ListStreamTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/7/9 17:36
 */
public class ListStreamTest {
    public static List<Integer> streamList = Arrays.asList(1,2,15,6,9,13,7,7,7,7);
    public static String[] stringlist = {"my","name","is","lambda","mzp"};

    public static void main(String[] args) {
        streamTest();
    }

    /**
     *  1.创建流
     *  2.中间操作
     *      注意：多个中间操作可以连城一个流水线，除非遇到终止操作，否则中间操作不会执行任何处理。当遇到终止操作
     *          之后，所有的中间操作一次性全部处理，这个就称为“惰性求值”
     *  3.终止操作
     *
     *
     *
     *  中间操作：
     * description: 使用stream打印集合元素.
     * 每个中间操作，又会返回一个Stream，比如.filter()又返回一个Stream, 中间操作是“懒”操作，并不会真正进行遍历。
     * 中间操作比较多，主要分两类
     * 对元素进行筛选 和 转换为其他形式的流
     * 对元素进行筛选：
     * filter 匹配
     * distinct 去除重复(根据equals判断)
     * sorted 自然排序
     * sorted(Comparator<T>) 指定排序
     * limit 保留
     * skip 忽略
     * 转换为其他形式的流
     * mapToDouble 转换为double的流
     * map 转换为任意类型的流。接收lambda，将元素"转换成其他形式"或者"提取信息"，接收一个函数作为参数，该函数会作用在每个元素上(类似遍历动作)，并且将其映射成一个新的元素。
     *
     *  终止操作：
     * 当进行结束操作后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。 结束操作不会返回Stream，但是会返回int、float、String、 Collection或者像forEach，什么都不返回,。
     * 结束操作才真正进行遍历行为，前面的中间操作也在这个时候，才真正的执行。
     * 常见结束操作如下：
     * forEach() 遍历每个元素
     * toArray() 转换为数组
     * allMatch 检查是否匹配所有元素
     * anyMatch 检查是否至少匹配一个元素
     * noneMatch 检查是否没有匹配所有元素
     * min(Comparator<T>) 取最小的元素
     * max(Comparator<T>) 取最大的元素
     * count() 总数
     * findFirst() 第一个元素
     * findAny 返回当前流中的任意元素
     * 归约：
     * reduce (T identity, Binaryoperator) / reduce (Binaryoperator)一可以将流中元素反复结合起来，得到一个值
     * collect 将流转换为其他形式。接收-一个Collector接口的实现，用于给stream中元素做汇总的方法
     *
     * @Author maozp3
     * @Date: 13:23 2020/4/28
     * @Param
     * @return
     **/
    public static void streamTest(){
        System.out.println("使用stream遍历集合:");
        streamList.stream().forEach(num -> System.out.print(num+" "));
        System.out.println("\nfilter(匿名表达式)：根据条件匹配合适的数据:");
        streamList.stream().filter(num -> num >= 6).forEach(num -> System.out.print(num + " "));
        System.out.println("\ndistinct()：用来去重:");
        streamList.stream().distinct().forEach(num -> System.out.print(num+" "));
        System.out.println("\nsorted():自然排序：升序");
        streamList.stream().sorted().forEach(num -> System.out.print(num+" "));
        System.out.println("\nsorted(自定义比较器)：排序.这里我期望“后面的数a比前面的数b小”，所以是降序");
        streamList.stream().sorted((a, b) -> a <= b ? 1 : -1).forEach(num -> System.out.print(num + " "));
        System.out.println("\n先去重，再排序。sorted(自定义比较器)：排序.这里我期望“后面的数a比前面的数b小”，所以是降序");
        streamList.stream().distinct().sorted((a, b) -> a <= b ? 1 : -1).forEach(num -> System.out.print(num + " "));
        System.out.println("\nlimit(n):保留前n个数");
        streamList.stream().limit(2).forEach(num -> System.out.print(num+" "));
        System.out.println("\nskip(n):跳过前n个数");
        streamList.stream().skip(2).forEach(num -> System.out.print(num+" "));
        System.out.println("\nskip(n):(parallelStream获取并行流，多个线程去处理)跳过前n个数");
        streamList.parallelStream().skip(2).forEach(num -> System.out.print(num+" "));




        // 创建无限流
        System.out.println("\nStream.iterate(种子起点,操作)：来创建无限流。这里是创建从0开始的偶数，并且取了前10个");
        Stream.iterate(0,(x)->x+2).limit(10).forEach(num-> System.out.println(num));

        System.out.println("\nStream.generate(Supplier<T> s)：来创建无限流。这里是生成随机数,并且只取了前5个");
        Stream.generate(()-> (int)(Math.random()*10)).limit(5).forEach(System.out::println);




        //数组无法直接调用stream()，  只能通过Stream.of(要操作的数组)  或者   Arrays.stream(要操作的数组)  或者  把数组转成list之后，在调用stream()
        System.out.println("\n\n\nStream.of(stringList)的方式，对stringList的元素遍历：");
        Stream.of(stringlist).forEach(e -> System.out.print(e + " "));
        System.out.println("\nArrays.stream(stringList)的方式，map()打印stringList中每个字符串的长度：");
        Arrays.stream(stringlist).map(e -> e.length()).forEach(e -> System.out.print(e + " "));
        System.out.println("\n把数组转换成list的方式，对stringList的元素遍历：");
        Arrays.asList(stringlist).stream().forEach(e -> {
            System.out.print(e + " ");
            System.out.println(e.getClass());
        });




        //将map转化为list.  终端操作调用  collect(Collectors.toList()
        Map<String,Object> map = new HashMap<>();
        map.put("name","mzp");
        map.put("age",24);
        map.put("sex","男");
        Integer a = 0; a.compareTo(a);
        System.out.println("\n遍历map：");
        map.entrySet().stream().forEach(entry-> System.out.print(entry+"  "));
//        map.entrySet().stream().forEach(System.out::println);
        System.out.println("\nmap()将map转换为list：");
        List listFromMap = map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        listFromMap.stream().map(e->e.equals("男")).forEach(e-> System.out.print(e+" "));  //将map中的元素逐个与"男"进行比较，最后输出true或false
        listFromMap.stream().forEach(e-> System.out.print(e+" ")); //打印list中的元素
        System.out.print("\nlistFromMap="+listFromMap);  //打印list中的元素

        //将list转为set
        Set setFromList = Arrays.stream(stringlist).collect(Collectors.toSet());
        System.out.println("\n将数组先变为list，再把list变为set="+setFromList);
        System.out.println("\nSet可以调用stream()流：");
        setFromList.stream().forEach(e-> System.out.println(e));
 
    }


    // ========================================================================
    // ========================================================================
    // ========================================================================
    /**
     * 内部迭代：由Stream API完成
     */
    @Test
    public void test1(){
        /**
         * 多个中间操作可以连城一个流水线，除非遇到终止操作，否则中间操作不会执行任何处理。当遇到终止操作
         *    之后，所有的中间操作一次性全部处理，这个就称为“惰性求值”
         */
        Stream stream =  streamList.stream().filter((e)-> {
            System.out.println("Stream API的中间操作filter");  //如果没有终止操作，这里的中间操作都不会执行
            return e>5;
        });

        /**
         * 终止操作：一次性执行完全部内容，即“惰性求值”
         */
        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        streamList.stream().filter((e)-> {
            System.out.println("Stream API的中间操作filter");  //如果没有终止操作，这里的中间操作都不会执行
            return e > 5;
        })
        .limit(2)   //保留前两个满足条件的。这里并没有遍历全部，而是找到两个之后，就不再遍历了，也就是类似“短路”，提高了效率
        .forEach((result)-> System.out.println(result));
    }

    @Test
    public void test3(){
        streamList.stream().filter((e)->
//            System.out.println("Stream API的中间操作filter");  //如果没有终止操作，这里的中间操作都不会执行
             e > 5
        )
        .skip(2)   //跳过前两个元素
        .distinct()   // 去重是通过hashCode()和equals()来去重的，所以要重写这两个方法。尤其是如果是自定义的实体类的话，要重写这两个方法，否则去重不生效
        .forEach((result)-> System.out.println(result));
    }


    List<UserInfo> userInfoList = Arrays.asList(
            new UserInfo("张三","123",21),
            new UserInfo("李四","456",22),
            new UserInfo("王五","789",23),
            new UserInfo("赵六","456",24),
            new UserInfo("田七","456",25),
            new UserInfo("田七","456",25)
    );
    /**
     *  映射：
     *  map===》接收lambda，将元素"转换成其他形式"或者"提取信息"，接收一个函数作为参数，该函数会作用在每个元素上，并且将其映射成一个新的元素。
     *  flatMap===》接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","mzp");
        List<String> listTest = new ArrayList<>();
        list.stream()
                .map(str -> str.toUpperCase() )     //这个lambda表达式被作用在每个元素上面。
                .forEach((str)-> System.out.println(str));

        System.out.println("==============Map映射=====================");
        userInfoList.stream()
                .map(userInfo -> userInfo.getUsername())  //提取信息，这个lambda表达式被作用在每个元素上面。
                .forEach(result-> System.out.println(result));

        System.out.println("==============Map映射测试遍历=====================");
        List<String> list1 =  userInfoList.stream()
                .map(userInfo -> userInfo.getUsername())  //提取信息，这个lambda表达式被作用在每个元素上面。
                .collect(Collectors.toList());
        list1.stream().forEach(s-> System.out.println(s));

        System.out.println("==============Map映射取字符=====================");
        Stream<Stream<Character>> stream = list.stream()
                //map本身返回的就是一个Stream，它里面的lambda返回的是一个Stream<Character>，所以最后返回的是Stream<Stream<Character>>
                //这里相当于是把一个一个的流，都放入了一个大流里面：{ {a,a,a},{b,b,b},{c,c,c},......}
                .map(ListStreamTest::filterCharacter);
        stream.forEach(sm-> sm.forEach(ch-> System.out.println(ch)));

        System.out.println("==============flatMap映射取字符=====================");
        Stream<Character> streamFlat = list.stream()
                //这个相当于是一个一个流里面的东西取出来，重新放到了另一个新流里面：{a,a,a,b,b,b,c,c,c,......}
                .flatMap(ListStreamTest::filterCharacter);
        streamFlat.forEach(ch-> System.out.println(ch));

    }

    /** 取出单个字符。  返回一个流
    * @Description:
    * @Author maozp3
    * @Date: 13:21 2020/4/26
    * @Param [str]
    * @return java.util.stream.Stream<java.lang.Character>
    **/
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for(Character ch : str.toUpperCase().toCharArray()){
            list.add(ch);
        }

        return list.stream();
    }

    /**
     *  终止操作之 归约：
    * 归约：
    *   reduce (T identity, Binaryoperator) / reduce (Binaryoperator)一可以将流中元素反复结合起来，得到一个值
    *
    * @Author maozp3
    * @Date: 12:56 2020/4/29
    * @Param []
    * @return void
    **/
    @Test
    public void test5(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        // reduce的第一个参数是起始值（种子），把种子作为x传递下去进行运算，然后把结果作为新的种子，再传给x，再去进行运算.....。最后返回种子
        Integer sum = list.stream().reduce(100,(x,y)->x+y);  //这里对集合做了累加操作，增加了100 （这里有起始值，所以结果必定不会为空）
        System.out.println("累加的结果："+sum);

        /**
         * 重点：map-reduce链接，通过被称为map-reduce模式，大数据处理。
         *      先用map做数据提取，再用reduce做归约操作。
         */
        System.out.println("计算年龄总和：");
       Optional<Integer> ageSumOp =  userInfoList.stream()
                .map(e->e.getAge())     // 先使用map映射，提取出来年龄
                .reduce(Integer::sum);  // 在对年龄进行求和。（这里没有起始值，结果可能为空。所以返回的是Optional来避免空指针）
        System.out.println(ageSumOp.get());  // 打印Optional的值
    }
    /**
     * 去空格，去重，过滤
     * @author mao.zongpeng
     * @date 2022/11/29 16:25
     */
    @Test
    public void test7(){
        // 将字符串转换为数组，
        String strSrc = "170004,  170005,170009,170025  ,170044,170071,170137,170227,170270,170276,170285,170382,170423,170429,170516,170519,170566,170618,170627,170659,170708,170914,171000,171006,171087,171135,171188,171761,172142,172159,172174,172212,172350,172435,172459,172547,172731,172737,172754,172953,173213,173238,173409,173417,173815,174588,174718,174897,174932,174934,175231,175491,175620,176328,176568";
        String[] split1 = strSrc.split(",");
        // 将数组split1元素去空格后，转换为集合
        List<String> collectSrc = Arrays.stream(split1).map(String::trim).collect(Collectors.toList());

        String strOld = "170004,170005,170009,170025,170030,170031,170044,170071,170137,170227,170270,170276,170285,170382,170423,170429,170516,170519,170566,170618,170627,170659,170708,170914,170919,171000,171006,171087,171135,171188,171761,172142,172159,172174,172212,172350,172435,172459,172547,172731,172737,172754,172953,173213,173238,173409,173417,173815,174588,174718,174897,174932,174934,175231,175491,175620,176328,176568";
        String[] split2 = strOld.split(",");
        List<String> collectOld = Arrays.stream(split2).map(String::trim).collect(Collectors.toList());
        // 从collect2中过滤出 不存在 collectTable中的元素
        List<String> resultList = collectOld.stream().filter(e -> !collectSrc.contains(e)).collect(Collectors.toList());

        resultList.forEach(System.out::println);
    }

    /**
     * 收集：
     * collect 将流转换为其他形式。接收-一个Collector接口（收集器）的实现（Collectors），用于给stream中元素做汇总的方法
     */
    @Test
    public void test6(){
        System.out.println("--------------收集到集合中list----------------");
        List<String> listName = userInfoList.stream()
                .map(e->e.getUsername())
                .collect(Collectors.toList());      //collect（Collector接口的实现）， 而Collectors就是Collector接口的实现类。
        listName.stream().forEach(System.out::println);

        System.out.println("--------------收集到set中去重----------------");
        Set<String> setName = userInfoList.stream()
                .map(e->e.getUsername())
                .collect(Collectors.toSet());
        setName.stream().forEach(System.out::println);


        System.out.println("--------------收集到LinkedList中----------------");
        LinkedList<String> linkedListName = userInfoList.stream()
                .map(e->e.getUsername())
                .collect(Collectors.toCollection(LinkedList::new));     // 这里toCollection(构造器)传入的是一个构造器。就是想要转换的那个类型的构造器
        linkedListName.stream().forEach(System.out::println);

        System.out.println("--------------收集到HashSet中----------------");
        HashSet<String> hashSetName = userInfoList.stream()
                .map(e->e.getUsername())
                .collect(Collectors.toCollection(HashSet::new));     // 这里toCollection(构造器)传入的是一个构造器。就是想要转换的那个类型的构造器
        hashSetName.stream().forEach(System.out::println);

        System.out.println("--------------总个数----------------");
        Long count = userInfoList.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("--------------平均值----------------");
        Double avg = userInfoList.stream()
                .collect(Collectors.averagingDouble(UserInfo::getAge));
        System.out.println(avg);

        System.out.println("--------------求和----------------");
        Double sum = userInfoList.stream()
                .collect(Collectors.summingDouble(UserInfo::getAge));
        System.out.println(sum);

        System.out.println("--------------最大值对象----------------");
        Optional<UserInfo> max = userInfoList.stream()
                .collect(Collectors.maxBy((n1,n2)->Double.compare(n1.getAge(),n2.getAge())));
        System.out.println(max.get());

        System.out.println("--------------最小值对象----------------");
        Optional<UserInfo> min = userInfoList.stream()
                .collect(Collectors.minBy((n1,n2)->Double.compare(n1.getAge(),n2.getAge())));
        System.out.println(min.get());

        System.out.println("--------------最大值数值----------------");
        Optional<Integer> maxNum = userInfoList.stream()
                .map(UserInfo::getAge)  // 取出对象中的某个数值。（map把每一个对象的该数值都取出来了）
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(maxNum.get());

        System.out.println("--------------最小值数值----------------");
        Optional<Integer> minNum = userInfoList.stream()
                .map(UserInfo::getAge)      // 取出对象中的某个数值。（map把每一个对象的该数值都取出来了）
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(minNum.get());

        System.out.println("--------------分组Group By----------------");
        Map<Integer,List<UserInfo>> map = userInfoList.stream()     // 分组的返回值是一个map,期中key是分组字段的所属类型，value是List中的对象的集合
                .collect(Collectors.groupingBy(UserInfo::getAge));
        System.out.println(map);
        // 遍历map
        for(Map.Entry<Integer,List<UserInfo>> entry : map.entrySet()){
            System.out.println("key is "+ entry.getKey());
            System.out.println("value is " + entry.getValue());
        }
    }

}
