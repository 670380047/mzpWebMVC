package util.study.io.serializer.serializerImpl;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import util.study.io.serializer.IMzpSerialize;

import java.nio.charset.StandardCharsets;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 23:31
 * @File : XmlSerializer
 * @Software: IntelliJ IDEA 2019.2.04
 */

/**
 * 自定义的序列化接口的实现类：通过xml来实现.  需要引入xstream依赖
 */
public class XmlSerializer implements IMzpSerialize {

    // XStream的固定使用
    XStream xStream = new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T) xStream.fromXML(new String(data));
    }
}
