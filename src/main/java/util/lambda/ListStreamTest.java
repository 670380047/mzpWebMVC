package util.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/9 17:36
 * @File : ListStreamTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

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
     * map 转换为任意类型的流
     *
     *
     * 当进行结束操作后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。 结束操作不会返回Stream，但是会返回int、float、String、 Collection或者像forEach，什么都不返回,。
     * 结束操作才真正进行遍历行为，前面的中间操作也在这个时候，才真正的执行。
     * 常见结束操作如下：
     * forEach() 遍历每个元素
     * toArray() 转换为数组
     * min(Comparator<T>) 取最小的元素
     * max(Comparator<T>) 取最大的元素
     * count() 总数
     * findFirst() 第一个元素
     * @author maozp3
     * @date: 17:22 2019/7/9
     * @param: []
     * @return void
     */
    public static void streamTest(){
        System.out.println("使用stream遍历集合:");
        streamList.stream().forEach(num -> System.out.print(num+" "));
        System.out.println("\nfilter(匿名表达式)：根据条件匹配合适的数据:");
        streamList.stream().filter(num->num>=6).forEach(num -> System.out.print(num+" "));
        System.out.println("\ndistinct()：用来去重:");
        streamList.stream().distinct().forEach(num -> System.out.print(num+" "));
        System.out.println("\nsorted():自然排序：升序");
        streamList.stream().sorted().forEach(num -> System.out.print(num+" "));
        System.out.println("\nsorted(自定义比较器)：排序.这里我期望“后面的数a比前面的数b小”，所以是降序");
        streamList.stream().sorted( (a,b)->a<=b?1:-1  ).forEach(num -> System.out.print(num+" "));
        System.out.println("\n先去重，再排序。sorted(自定义比较器)：排序.这里我期望“后面的数a比前面的数b小”，所以是降序");
        streamList.stream().distinct().sorted( (a,b)->a<=b?1:-1  ).forEach(num -> System.out.print(num+" "));
        System.out.println("\nlimit(n):保留前n个数");
        streamList.stream().limit(2).forEach(num -> System.out.print(num+" "));
        System.out.println("\nskip(n):跳过前n个数");
        streamList.stream().skip(2).forEach(num -> System.out.print(num+" "));


        //数组无法直接调用stream()，  只能通过Stream.of(要操作的数组)  或者   Arrays.stream(要操作的数组)  或者  把数组转成list之后，在调用stream()
        System.out.println("\n\n\nStream.of(stringList)的方式，对stringList的元素遍历：");
        Stream.of(stringlist).forEach(e-> System.out.print(e+" "));
        System.out.println("\nArrays.stream(stringList)的方式，map()打印stringList中每个字符串的长度：");
        Arrays.stream(stringlist).map(e->e.length()).forEach(e-> System.out.print(e+" "));
        System.out.println("\n把数组转换成list的方式，对stringList的元素遍历：");
        Arrays.asList(stringlist).stream().forEach(e->{System.out.print(e+" "); System.out.println(e.getClass());} );

        //将map转化为list.  终端操作调用  collect(Collectors.toList()
        Map<String,Object> map = new HashMap<>();
        map.put("name","mzp");
        map.put("age",24);
        map.put("sex","男");
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
 
    }
}
