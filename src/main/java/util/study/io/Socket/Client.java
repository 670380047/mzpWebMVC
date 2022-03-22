package util.study.io.Socket;

import util.study.io.Person;
import util.study.io.Student;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 20:12
 * @File : Client
 * @Software: IntelliJ IDEA 2019.2.04
 */

/**
 * Socket的客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        // 建立一个Socket链接，访问的是 127.0.0.1:8080 这个地址
        Socket socket = new Socket("localhost", 8080);
        try {
            Person mzp = new Person();
            mzp.setName("毛宗鹏");
            mzp.setAge(18);
            mzp.setNation("这是一个transient 关键字表示该属性是临时的，不会被序列化");
            mzp.setStudent(new Student(1));

            // 以socket为节点流来创建一个 对象输出流，用于把对象传输到socket这个节点流上（这里就是指定的网络地址上）
            // 用于将内存的对象序列化到网络中去传输
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mzp);

        } finally {
            // 关闭网络
            socket.close();
            // 只需要关闭最后一个处理流即可。他会关闭前面所有的，包括节点流
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }


    }
}
