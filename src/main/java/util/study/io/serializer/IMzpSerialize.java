package util.study.io.serializer;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 23:12
 * @File : IMzpSerialize
 * @Software: IntelliJ IDEA 2019.2.04
 */

/**
 * 自定义的序列化接口: 有两个方法  序列化serialize()    反序列化 deserialize()
 */
public interface IMzpSerialize {

    /**
     * 序列化对象
     * @param obj 需要被序列化的对象
     * @param <T> 对象的泛型
     * @return
     */
    <T> byte[] serialize(T obj);


    /**
     * 反序列化对象
     * @param data  需要被反序列化的对象的数据
     * @param clazz 数据即将成为的对象的类型
     * @param <T>   对象的泛型
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
