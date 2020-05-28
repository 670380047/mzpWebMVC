package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/27 16:46
 * @File : TestCollections
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.*;

/**     Collections工具类，用来操作集合。   操作数据的工具类是 Arrays
 *      都是static方法。
 *
 *      注意！！！！
 *          排序和查找的时候，定义的比较器，返回的结果意义不同。
 *          排序（sort）：返回1，表示已经满足，不需要调换元素位置； 返回-1，表示不满足，需要调换元素位置
 *          查找（max/min）:返回1，表示大于；  返回-1，表示小于
 *
 *  一、排序操作
 *      reverse(List):反转List中元素的顺序
 *      shuffle(List):对List集合元素进行随机排序
 *      sort(List):根据元素的自然顺序对指定List集合元素按 “升序” 排序
 *      sort(List, Comparator): 根据指定的Comparator产生的顺序对List集合元素进行排序
 *      swap(List, int, int): 将指定list集合中的i处元素和j处元素进行交换
 *
 *   二、查找、替换
 *      Object max(Collection): 根据元素的自然顺序，返回给定集合中的最大元素
 *      Object max(Collection, Comparator): 根据Comparator指定的顺序，返回给定集合中的最大元素
 *      Object min(Collection)
 *      Object min(Collection, Comparator )
 *      int frequency(Collection, object): 返回指定集合中指定元素的出现次数
 *      void copy(List dest,List src):将src中的内容复制到dest中
 *      boolean replaceAl1(List list, Object oldVal, Object newVal): 使用新值替换List对象的所有旧值
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/27 16:46
 */
public class TestCollections {
    /**
     * 一、排序操作
     *     reverse(List):反转List中元素的顺序
     *     shuffle(List):对List集合元素进行随机排序
     *     sort(List):根据元素的自然顺序对指定List集合元素按 “升序” 排序
     *     sort(List, Comparator): 根据指定的Comparator产生的顺序对List集合元素进行排序
     *     swap(List, int, int): 将指定list集合中的i处元素和j处元素进行交换
     */
    @Test
    public void test1(){
        List list = new ArrayList();
        list.add("aa");
        list.add("cc");
        list.add("bb");
        list.add("dd");
        list.add("ee");
        System.out.println("原始list的内容："+list);

        Collections.reverse(list);
        System.out.println("反转后的list的内容："+list);

        Collections.shuffle(list);
        System.out.println("list的内容随机排序："+list);

        Collections.sort(list);
        System.out.println("对list中的内容进行升序排序："+list);

        //排序(匿名函数)
        Collections.sort(list, new Comparator<String>(){
            //使用新的排序规则，根据第一个字符进行逆排序
            @Override
            public int compare(String a, String b) {
                if(a.charAt(0) <= b.charAt(0)){
                    return 1;       // 返回1： 不需要调换位置
                }else{
                    return -1;      // 返回-1： 需要调换位置
                }
            }
        });
        System.out.println("使用sort对list实现自定义排序："+list);

        Collections.swap(list,0,list.size()-1);  // 这里调换第一个元素和最后一个元素的位置
        System.out.println("swap指定调换list中两个元素的位置："+list);
    }

    /**
     * 二、查找、替换
     *     Object max(Collection): 根据元素的自然顺序，返回给定集合中的最大元素
     *     Object max(Collection, Comparator): 根据Comparator指定的顺序，返回给定集合中的最大元素
     *     Object min(Collection)
     *     Object min(Collection, Comparator )
     *     int frequency(Collection, object): 返回指定集合中指定元素的出现次数
     *     void copy(List dest,List src):将src中的内容复制到dest中
     *     boolean replaceAl1(List list, Object oldVal, Object newVal): 使用新值替换List对象的所有旧值
     */
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add("aa");
        list.add("cc");
        list.add("bb");
        list.add("dd");
        list.add("ee");
        list.add("aa");
        System.out.println("原始list的内容："+list);

        Object max = Collections.max(list);
        System.out.println("集合中的最大值（自然排序方式下的）:"+max);


        Collections.sort(list,new myDiyComparator());
        System.out.println("使用sort对list实现自定义排序："+list);
        Object maxDiy = Collections.max(list, new Comparator<String>(){
            /**
             * 使用新的排序规则，根据第一个字符进行比较：
             *  第一个字符越小，就说这个元素越大（返回1）。
             */
            @Override
            public int compare(String a, String b) {
                if(a.charAt(0) <= b.charAt(0)){
                    // 含义不同。在max或min这里你返回1，就证明他是大的
            // 返回1： 在max查找最大值的时候，这里是大于的意思。 在sort方法排序的时候是：表示满足，“不需要调换位置”
                    return 1;
                }else{
                    // 含义不同。在max或min这里你返回-1，就证明他是小的
            // 返回-1： 在max查找最大值的时候，这里是小于的意思。在sort方法排序的时候是：表示不满足，“需要调换位置”
                    return -1;
                }
            }
        });
        System.out.println("根据自定义的比较器，来获取list中的最大值："+maxDiy);

        Integer n = Collections.frequency(list,"aa");
        System.out.println("frequency查看指定元素在指定集合中出现的次数："+n);

        /**
         * 注意：
         * copy方法需要dest集合的大小要  大于等于  src集合的大小
         */
//        List list2 = new ArrayList(list.size()); // 不可行
//        Collections.copy(list2,list);

        /**
         * 借用数组来创建指定大小的数组。
         * 然后再把数组转成集合。  即可。
         *      而且，此时由数组转换过来的list，不能再添加新的数据了，
         *          这个集合不是ArrayList类的，而是Arrays的一个内部类ArrayList。
         */
        Object[] objs = new Object[list.size()];
        List destList = Arrays.asList(objs);
        Collections.copy(destList,list);
//        destList.add("zz");  // 出错：UnsupportedOperationException
        System.out.println("copy(dest,src),将src中的内容复制到dest中，dest："+destList);

        Collections.replaceAll(list,"aa","mm");
        System.out.println("replaceAll将指定集合中的指定元素全部替换成另一个元素:"+list);
    }


    /**
     * 内部类。 自定义的比较器。  根据字符串的第一个字符，进行逆序 排序
     */
    class myDiyComparator implements  Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            if( !(o1 instanceof  String && o2 instanceof String) )
            {
                return 0;
            }
            String str1 = String.valueOf(o1);
            String str2 = String.valueOf(o2);
            if(str1.charAt(0) <= str2.charAt(0)){
                return 1;       // 返回1： 不需要调换位置。这里返回1是说明满足条件，不需要调换位置。
            }else{
                return -1;      // 返回-1： 需要调换位置。这里返回-1是说明不满足条件，需要调换位置。
            }
        }
    }
}
