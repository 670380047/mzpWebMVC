package util.study.Designpattern.factory.simpleFactory;

/**
 * 工厂类。
 * 里面有一个创建对象的方法：
 *      根据传入的参数不同，来创建不同的接口对象。
 *      参数用泛型限制，限制为 T 类型及其子类，并返回T类型。 （面向接口编程）
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:38
 * @File : CourseFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class CourseFactory {
    /**
     * 传入一个接受ICourse接口的子类的 Class类型
     * @param clazz
     * @return
     */
    public <T> T createBean(Class<? extends T> clazz){
        if(clazz != null){
            try {
                // 通过反射创建实例，调用无参构造方法创建实例的方法二
                return clazz.newInstance();
                // 通过反射创建实例，调用无参构造方法创建实例的方法一
//                Constructor<? extends ICourse> constructor = clazz.getConstructor();
//                return constructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 否则创建对象实例失败
        return null;
    }
}
