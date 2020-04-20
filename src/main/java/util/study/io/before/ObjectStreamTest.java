package util.study.io.before;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/18 10:38
 * @File : ObjectStreamTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;
import util.study.io.Person;
import util.study.io.Student;

import java.io.*;

/**     对象流、数据流  都是处理流中的一种。
 *      数据流只能存储序列化8个个几本类型和包装类型。 对象流更加强大，还可以序列化对象。因此选择对象流。
 * 数据流（略，和对象流操作一样，但是功能弱）：DataInputStream、DataOutputStream
 * 1.对象流：ObjectInputStream   ObjectOutputStream
 *      序列化：将内存中的对象永久的以二进制的形式保存到磁盘中
 *          对象序列化步骤：
 *              1.1创建节点流
 *              1.2创建缓冲流来包装节点流，提高效率（可选）
 *              1.3创建对象流来包装节点流。（如果此时节点流被其他处理流包装了，比如被缓冲流包装了，那么这里对象流就是来包装缓冲流，而不是节点流。因为要一层一层的来包装）
 *              1.4完成对象序列化：   objectOutputStream.writeObject(p1);
 *              1.5关闭流
 *              注意：需要被序列化的对象以及其内部属性，都要实现java.io.Serializable接口
 *              提供一个序列号，如：private static final long serialVersionUID = -227600583788766568L;
 *      反序列化：将磁盘中保存的对象读取到内存中
 *
 *
 * 一、序列化
 * 二、反序列化
 * 三、对象序列化
 * 四、对象的反序列化
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/18 10:38
 */
public class ObjectStreamTest {
    /**
     * 一、序列化：将内存中的数据，永久的 “以二进制的形式”保存在磁盘中
     */
    @Test
    public void test1(){
        int num = 10;
        boolean flag = true;
        String str = "abcde";

        ObjectOutputStream objectOutputStream = null;
        try {
            /**
             * 1.创建字节输出节点流
             */
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resource/io/objectStream/objectStreamTest");
            /**
             * 2.创建对象流。来包装节点流
             */
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            /**
             * 3.保存对应的数据
             */
            objectOutputStream.writeInt(num);
            objectOutputStream.writeBoolean(flag);
            objectOutputStream.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 4.关闭流
             */
            try {
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 二、反序列化：将磁盘中保存的对象读取到内存中
     */
    @Test
    public void test2(){
        ObjectInputStream objectInputStream = null;
        try {
            /**
             * 1.创建节点输入流
             */
            FileInputStream fileInputStream = new FileInputStream("src/main/resource/io/objectStream/objectStreamTest");

            /**
             * 2.创建对象流的，来包装节点流
             */
            objectInputStream = new ObjectInputStream(fileInputStream);

            /**
             * 3.读取指定的数据
             *  注意：怎么写的，就怎么读。  比如上面先写 int   再写boolean  最后写String。  那么读的时候也是这个顺序
             */
            int num = objectInputStream.readInt();
            boolean flag = objectInputStream.readBoolean();
            String str = objectInputStream.readUTF();

            System.out.println(num);
            System.out.println(flag);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 4.关闭流
             */
            try {
                if(objectInputStream != null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 三、对象序列化
     */
    @Test
    public void test3(){
        Person p1 = new Person("张三",18,"中国");
        Person p2 = new Person("李四",20);
        Person p3 = new Person("王五",21,new Student(19));

        ObjectOutputStream objectOutputStream = null;
        try {
            /**
             * 1.创建字节输出节点流
             */
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resource/io/objectStream/person");
            /**
             *  2.创建处理流。一个节点流可以被多个处理流包装，一层一层的包装
             *       2.1创建字节输出缓冲流，包装节点流，用来提高效率
             *       2.2创建字节输出对象流，包装缓冲流，用来完成对象的序列化
             */
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            /**
             * 3.完成序列化
             */
            objectOutputStream.writeObject(p1);
            objectOutputStream.writeObject(p2);
            objectOutputStream.writeObject(p3);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 4.关闭流。只需要关闭最后一个处理流就行了。
             */
            try {
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 四、对象的反序列化
     */
    @Test
    public void test4(){

        ObjectInputStream objectInputStream = null;
        try {
            /**
             * 1.创建字节输入节点流
             */
            FileInputStream fileInputStream = new FileInputStream("src/main/resource/io/objectStream/person");
            /**
             *  2.创建处理流。一个节点流可以被多个处理流包装，一层一层的包装
             *       2.1创建字节输入缓冲流，包装节点流，用来提高效率
             *       2.2创建字节输入对象流，包装缓冲流，用来完成对象的序列化
             */
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);
            /**
             * 读取内容。
             * 序列化的顺序是什么，这里反序列化的顺序就得是什么样的
             */
            Object object = objectInputStream.readObject();
            Person p1 = (Person) object;
            Person p2 = (Person) objectInputStream.readObject();
            Person p3 = (Person) objectInputStream.readObject();
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            /**
             * 4.关闭缓冲流
             */
            try {
                if(objectInputStream != null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
