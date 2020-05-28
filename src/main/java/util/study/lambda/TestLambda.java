package util.study.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/5 14:49
 * @File : TestLambda
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.*;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/7/5 14:49
 */
public class TestLambda {
    public static List<String> list = Arrays.asList("my","name","is","lambda","mzp");
    public static List<Integer> integerList = Arrays.asList(1,2,15,6,9,13,7);

    public static void main(String[] args) {


        System.out.println("\n排序前：");
        printList(integerList);
        oldIntegerSort();
        System.out.println("\noldSort排序后：");
        printList(integerList);

        newIntegerSort();
        System.out.println("\nnewSort排序后：");
        printList(integerList);

//        System.out.println("排序前：");
//        printList(list);
//        oldSort();
//        System.out.println("\noldSort排序后：");
//        printList(list);
//        newSort();
//        System.out.println("\nnewSort排序后：");
//        printList(list);
    }




    /**
     * @Author maozp3
     * @Description: 对String类型的lis就行排序。使用老方法（外部比较器Comparator）
     * @Date: 14:51 2019/7/5
     * @Param []
     * @return void
     **/
    public static void oldIntegerSort(){
        //排序(匿名函数)
        Collections.sort(integerList, new Comparator<Integer>(){
            //使用新的排序规则。比较器排序。
            // 原理，先确定部分元素的顺序（升序或是降序），然后把剩余的元素通过"二分插入"进行排序。
            @Override
            public int compare(Integer a, Integer b) {  //源码中第一个入参（a）是数组靠后面的数(被计较的数)，第二个入参（b）是数组靠前面的数（比较的数）（比如这里：a=2，b=3）
                if(a <= b){    //由条件加上返回值来确定是升序还是降序 （如果全部返回-1的话，则实现逆序，将集合中的元素顺序颠倒）
                    return 1;   //满足条件，返回1，1则表示这个顺序不需要调整。  比如这里，原数组中前俩顺序是升序，如果返回1的话，结果就会使升序。但实际情况，根据我们定义的条件，这里返回的是-1
                }else{
                    return -1;  //不满足条件，返回-1,-1则表示数组中现在的顺序需要调整。 比如这里，原数组中前俩顺序时升序，如果返回-1的话，结果就需要调整顺序。实际情况，根据我们定义的条件，这里确实是返回-1
                }
            }
        });
    }

    /**
     * @Author maozp3
     * @Description: 新的排序方法 - 使用lambda表达式
     * @Date: 15:06 2019/7/5
     * @Param []
     * @return void
     **/
    public static void newIntegerSort(){
        //lambda会自动推断出a,b的类型
        Collections.sort(list,(a,b)->a.charAt(1)<=b.charAt(1)?1:-1);
    }

    /**
     * @Author maozp3
     * @Description: 打印集合元素    泛型方法的定义：在方法返回值前声明一下<T>,任意字母都可以比如<M>，表名该方法是泛型方法
     * @Date: 10:38 2019/7/8
     * @Param [list]
     * @return void
     **/

    public static <T> void printList(List<T> list){
        Iterator<T> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }
    }


    /**
    * @Author maozp3
    * @Description: 对String类型的lis就行排序。使用老方法（外部比较器Comparator）
    * @Date: 14:51 2019/7/5
    * @Param []
    * @return void
    **/
    public static void oldSort(){
        //排序(匿名函数)
        Collections.sort(list, new Comparator<String>(){
            //使用新的排序规则，根据第二个字符进行逆排序
            @Override
            public int compare(String a, String b) {
                if(a.charAt(1) <= b.charAt(1)){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }

    /**
    * @Author maozp3
    * @Description: 新的排序方法 - 使用lambda表达式
    * @Date: 15:06 2019/7/5
    * @Param []
    * @return void
    **/
    public static void newSort(){
        //lambda会自动推断出a,b的类型
        Collections.sort(list,(a,b)->a.charAt(1)<=b.charAt(1)?1:-1);
    }






}
