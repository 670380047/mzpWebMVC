package util.study.Designpattern.singleton;

import java.io.*;

/**
 * 【单例模式】：防止反序列化破坏单例
 *      场景：把对象反序列化到内存的时候是需要重新分配内存的，即重新创建一个对象。这个时候就会破坏单例
 *      解决：我们让他重新创建的那个对象依旧是我们的单例对象，就可以了
 *          实现方式，让单例对象类添加一个方法：
 *                  private Object readResolve(){
 *                       return SingletonHolder.instance;
 *                   }
 *           因为反序列化之前会判断类中有没有这个readResolve()方法，
 *           没有的话，就会返回新创建的对象实例。如果有的话，就会通过反射调用这个方法，用他的值覆盖掉前面的新对象实例。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/11 23:33
 * @File : SerializeObject
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SerializeObject {
    public static void main(String[] args) {
        // 先创建一个对象。下面把这个对象给序列化到磁盘
        Object instance = StaticInnerSingletonSerialize.getInstance();

        System.out.println("--------------序列化破坏单例-------------");

        // 这个instance2是将来的反序列化对象。 将来就是比较instance是不是同一个对象
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

            // 关闭输入流（最好是在finally中关闭）
            objectInputStream.close();

            System.out.println(instance);
            System.out.println(instance2);
            System.out.println("序列化和反序列化的对象是否相等："+(instance == instance2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

        }
    }

}

class StaticInnerSingletonSerialize implements Serializable{
    private static final long serialVersionUID = 4226761010800467979L;

    /**
     * 1.定义一个静态内部类。 类里面定义了一个外层类的静态实例，并且初始化了实例。
     *      静态内部类：（利用了java的语法规则，实现延迟加载）加载外部类的时候并不会给静态内部类分配空间。只有在用到静态内部类的时候才会分配空间。
     */
    private static class SingletonHolder{
        // 声明了一个外部类类型的静态变量，用来保存单例对象
        private static  StaticInnerSingletonSerialize instance;
        //  这个静态快主要是来捕获实例化时可能产生的异常的。  这个异常是反射创建实例的时候会产生的。
        static {
            try {
                instance = new StaticInnerSingletonSerialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 2.私有化构造方法
     *      抛出异常是为了防止“通过反射强制访问私有构造器”来创建实例，从而破坏了单例。
     */
    private StaticInnerSingletonSerialize() throws Exception {
        // 这一步的"判断过程"就会用到静态内部类，直接就加载类，然后实例化了。 所以第一次返回的就是true
        // 因此如果用反射的话，这里一定会返回true，然后抛出异常
        // 这个操作同样适用于【饿汉模式】，因为他们都是类加载过程中实例化单例的，用来“防止反射破坏单例”
        if(SingletonHolder.instance != null ){
            throw new Exception("不允许非法访问 ");
        }
    }

    /**
     * 3.对外提供public 静态方法。
     *      final保证这个方法不会被重写
     * @return
     */
    public static final StaticInnerSingletonSerialize getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * 防止反序列化破坏单例的关键！！！
     * 重写这个readResolve方法（名字不能错），就可以防止反序列化破坏单例了。
     *      在反序列化的时候，会检查对象有没有这个方法：
     *          如果没有的话，就会用新创建的新实例来返回。
     *          如果有的话，会调用这个方法，因此可以用这个方法返回一个单例的实例。然后这个实例会覆盖之前的新实例，来返回。
     * @return
     */
    private  Object readResolve(){
        return SingletonHolder.instance;
    }
}