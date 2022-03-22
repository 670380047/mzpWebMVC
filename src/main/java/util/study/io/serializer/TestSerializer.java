package util.study.io.serializer;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 23:22
 * @File : TestSerializer
 * @Software: IntelliJ IDEA 2019.2.04
 */

import util.study.io.Person;
import util.study.io.Student;
import util.study.io.serializer.serializerImpl.FastJsonSerializer;
import util.study.io.serializer.serializerImpl.XmlSerializer;

import java.util.HashMap;

/**
 * 自定义的序列化接口: 测试类
 */
public class TestSerializer {

    // 定义两个序列化方式的常量
    private static final String TYPE_FOR_JSON ="json";
    private static final String TYPE_FOR_XML ="xml";

    // 定义一个序列化容器，里面已经准备好的序列化的实现类
    private static final HashMap<String, IMzpSerialize> serializeHashMap;

    // 在静态代码块里面初始化序列化容器，初始化自定义的序列化实现类
    static {
        serializeHashMap = new HashMap<String, IMzpSerialize>() {{
            put(TYPE_FOR_JSON, new FastJsonSerializer());
            put(TYPE_FOR_XML, new XmlSerializer());
        }};
    }


    public static void main(String[] args) {
        test(TYPE_FOR_JSON);
        System.out.println("==============================================================================================");
        test(TYPE_FOR_XML);

    }

    /**
     * 测试类。测试序列化和反序列化的方式
     *
     * @param type
     */
    private static void test(String type) {
        // 策略模式。根据不通的类型，来获取不同的自定义的序列化方式。
        // 向上转型：为了调用父类中被子类重写的方法
        IMzpSerialize serialize = serializeHashMap.get(type);

        System.out.println(type + "格式的序列化：");

        Person mzp = new Person();
        mzp.setName("毛宗鹏");
        mzp.setAge(18);
        mzp.setNation("这是一个transient 关键字表示该属性是临时的，不会被序列化");
        mzp.setStudent(new Student(2));

        // 将mzp对象序列化成字节
        byte[] bytes = serialize.serialize(mzp);
        // 输出一下这个字节的长度
        System.out.println("bytes length:" + new String(bytes).length());
        System.out.println("序列化的结果如下：");
        System.out.println(new String(bytes));

        // 将序列化后的bytes数组再反序列化成对象
        Person deserializeForPerson = serialize.deserialize(bytes, Person.class);
        System.out.println("\n\n反序列化的结果如下：");
        // 打印反序列化后的对象
        System.out.println(deserializeForPerson);
    }
}
