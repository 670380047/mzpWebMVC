package util.study.io.Socket;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/3/21 20:22
 * @File : Server
 * @Software: IntelliJ IDEA 2019.2.04
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket的服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建一个socket服务端，监听8080端口的
        ServerSocket serverSocket = new ServerSocket(8080);
        // accept等待被访问。（这里存在网络阻塞）
        Socket accept = serverSocket.accept();

        // 以socket为输入节点，创建一个输入节点流，
        ObjectInputStream objectInputStream = null;
        try {
            // 以socket为输入节点流，来往内存中输入数据。（即从网络读取数据，写入到内存中）  用于将网络传输过来的对象，反序列化
            objectInputStream = new ObjectInputStream(accept.getInputStream());
            Object o =  objectInputStream.readObject();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
            // 只需要关闭最后一个处理流即可。他会关闭前面所有的，包括节点流
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }
}
