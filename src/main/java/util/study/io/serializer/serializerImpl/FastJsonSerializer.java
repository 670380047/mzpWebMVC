package util.study.io.serializer.serializerImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import util.study.io.serializer.IMzpSerialize;

import java.nio.charset.StandardCharsets;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 23:13
 * @File : FastJsonSerializer
 * @Software: IntelliJ IDEA 2019.2.04
 */

/**
 * 自定义的序列化接口的实现类：通过FastJson来实现.  需要引入fastjson依赖
 *
 * fastjson优势：实现简单，用起来方便
 *
 * 1. 正常情况：如果只需要获得对象序列化后的json字符串，而不涉及反序列化还原对象时。使用。
 *
 * 2.SerializerFeature.WriteClassName
 *      需要把对象序列化后json字符串，重新反序列化为指定的对象。且对象的成员中包含有接口或抽象类。
 *      使用fastjson进行序列化的时候，当一个类中包含了一个接口（或抽象类）的时候，会将子类型抹去，只保留接口（抽象类），
 *          使得反序列化时没法拿到原始类型。
 *      为了解决这个问题，fastjson引入了AutoType，即在序列化的时候，把原始类型记录下来，保存在序列化结果中的一个@Type字段中。
 *          因为有了autoType功能，在反序列化时，就可以读取@Type的内容，进而把对象json内容反序列化成这个对象，
 *          并且调用这个类的setter方法来赋值
 */
public class FastJsonSerializer implements IMzpSerialize {
    @Override
    public <T> byte[] serialize(T obj) {

        /**
         * 1. 正常情况：如果只需要获得对象序列化后的json字符串，而不涉及反序列化还原对象时。使用。
         *      获得的是一个json字符串。
         */
        return JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8);


        /**
         * 2.SerializerFeature.WriteClassName
         * 需要把对象序列化后json字符串，重新反序列化为指定的对象。且对象的成员中包含有接口或抽象类。
         * 序列化的时候加入SerializerFeature.WriteClassName，那么序列化出来的json字符串就会给每个对象添加一个@type字段，
         *      用来记录这个对象的类型。方便在反序列化的时候能够直接还原成指定的类。尤其是某些类中包含有抽象类或接口作为成员的时候，
         *      此时这个成员的真正对象可能是接口的某一个子类，那么在反序列化的时候可以直接还原成对应的子类。
         *
         * 如果不添加这个参数的话，那么序列化出来的json字符串中的这个类包含有一个抽象类或接口作为成员的时候，
         *      此时这个成员的真正对象可能是接口的某一个子类，那么在反序列化的时候，不知道要反序列化成某一个具体的子类，在使用反序列
         *      化之后的对象进行强制类型转换的操作时，就会发生类型转换异常（classCastException）。
         */
//        return JSON.toJSONString(obj, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        // fastJson反序列化的时候，需要传入对象的类型
        return (T) JSON.parseObject(new String(data), clazz);
    }
}
