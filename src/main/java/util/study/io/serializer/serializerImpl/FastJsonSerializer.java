package util.study.io.serializer.serializerImpl;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
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
 */
public class FastJsonSerializer implements IMzpSerialize {
    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        // fastJson反序列化的时候，需要传入对象的类型
        return (T)JSON.parseObject(new String(data), clazz);
    }
}
