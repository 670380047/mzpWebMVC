package util.study.Designpattern.singleton;

import java.io.*;

/**
 * 【单例模式】：枚举式单例
 *      枚举不允许用反射创建实例。（虽然可以用反射获取到有两个参数的构造方法，但是不能创建实例，官方限制）
 *      优点：优雅（简洁）、防止反射破坏（不允许反射创建枚举实例）
 *      缺点：instance这个保存单例的变量是放在一个map容器中的（在类加载的时候初始化，同饿汉式一样），容易造成空间浪费
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/11 20:43
 * @File : TestEnumSingleton
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestEnumSingleton {
    public static void main(String[] args) throws Exception {
        Object instance = LazySingleton2.getInstance();

        System.out.println("--------------序列化破坏单例-------------");
        Object instance2 = null;
        try {
            //先把对象instance序列化到磁盘中: 先创建输出节点流
            FileOutputStream fileOutputStream = new FileOutputStream("serializeObj");
            //创建处理流来包装节点流：对象包装流来包装节点流
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(instance);
            objectOutputStream.flush();
            //关闭包装流即可。（包装流内部会关闭节点流）
            objectOutputStream.close();


            //开始反序列化,把磁盘中的对象读到内存中。 创建输入节点流
            FileInputStream fileInputStream = new FileInputStream("serializeObj");
            //创建处理流来包装节点流：对象包装流来包装节点流
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            instance2 =  objectInputStream.readObject();

            System.out.println(instance);
            System.out.println(instance2);
            System.out.println("序列化和反序列化的对象是否相等："+(instance == instance2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 【单例模式】。枚举式单例
 */
enum  EnumSingleton {
    /**
     * 保存单例属性。instance这个保存单例的变量是放在一个map容器中的（类加载的时候，初始化，同饿汉式一样）
     */
    INSTANCE;

    // 给单例随便创建一个属性
    private Object locateDateTime;

    /**
     * 获取单例的方法
     * @return
     */
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    public Object getLocateDateTime() {
        return locateDateTime;
    }

    public void setLocateDateTime(Object locateDateTime) {
        this.locateDateTime = locateDateTime;
    }
}