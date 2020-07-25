package util.study.reflect;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/28 16:27
 * @File : TestReflection
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 反射机制
 * 1. java程序的运行分为两种状态：
 *      1.1 编译时：通过javac命令，生成一个或多个.class字节码文件。（每个.class字节码文件对应一个类）
 *      1.2 运行时：通过java命令，将生成的一个或多个.class字节码文件加载到内存中。（由JVM提供的类加载器完成）
 * 2. 类：创建用于描述现实生活中的一类事物，若需要具体到某一个事物，需要通过new关键字，创建这个类的对象，
 *      然后用对象操作其属性、调用其方法。因为在编译的时候就能确定创建什么类的对象、操作什么属性、调用什么方法
 *      但是某种情况下，我们需要得知并使用一个在编译时完全未知的类，然后创建其对象、调用其方法。
 *
 * 3. 反射机制：被视为动态语言的关键，可以在运行时创建一个类的对象。在运行时，操作属性和调用方法
 *
 * 4. Class 是开启反射的源头
 *
 * 5. 获取Class 的实例的几种方式：
 *      5.1 通过运行时类的属性 class。 即 类名.class
 *      5.2 通过运行时类的对象的getClass()方法。 即 对象实例.getClass()
 *      5.3 通过Class类的静态方法 forName(String className)。 即：Class.forName("类的全名")  如：Class.forName("java.lang.Object") 和 Class.forName("util.study.reflect.TestReflection")
 *      5.4 通过类加载器（了解）。 如：参照下面的  二、类加载器读取配置文件Properties（对比原始方式）
 *
 * 6. 反射的功能
 *      6.1 可以在运行时得知任何一个对象所属的类
 *      6.2 可以在运行时创建任意一个类的对象实例
 *      6.3 可以在运行时判断一个对象的属性和方法
 *      6.4 可以在运行时调用一个对象的属性和方法
 *      6.5 生成动态代理
 *
 * 一、获取当前类被那个类加载器加载。ClassLoader
 *      Bootstrap Classloader 引导类加载器，这个无法直接获取，因为是c++编写的：负责java平台核心库
 *      ExtClassLoader  拓展类加载器：负责jre/lib/ext目录下的jar包 或 -D java.ext.dirs指定目录下的jar包
 *      AppClassLoader  系统类加载器:（是最常用的类加载器） 加载 java -classpath所指的目录下的类与jar包 或 -D java.class.path所指的目录下类和jar包
 *      Custom ClassLoader 自定义类加载器：通过java.lang.ClassLoader的子类来自定义加载class文件.属于应用程序根据自身需要自定义的classLoader.
 *                                      如tomcat、jBoss都会根据j2ee的规范自行实现ClassLoader
 * 二、类加载器读取配置文件Properties（对比原始方式）
 * 三、在运行时创建对象的实例
 *      类名.class方式。省去强转
 *      随便传进来一个类，获取他的类型名
 * 四、获取类的构造器Constructor
 * 五、获取类的属性Field
 * 六、获取类的方法Method
 * 七、获取运行时类的父类
 * 八、在运行时获取带泛型父类的类型
 *     如： util.study.reflect.Creature<java.lang.String>
 * 九、在运行时获取带泛型父类他的泛型类型（重要！！！！！）
 *      如：util.study.reflect.Creature<java.lang.String> 中的 java.lang.String
 * 十、在运行时获取运行时的接口
 * 十一、在运行时获取运行时类的内部类
 * 十二、在运行时获取运行时类的注解
 *         注解的生命周期需要设置运行时： @Retention(RetentionPolicy.RUNTIME)
 * 十三、在运行时获取运行时类属性的完整结构：
 *          修饰符、数据类型、数据名
 * 十四、在运行时获取运行时类方法的完整结果
 *      注解 修饰符  返回值类型  方法名（参数类型1 参数名1, 参数类型2 参数名2 .....） 异常
 *      包含注解的两种解析方式
 * 十五、在运行时获取并调用运行时类对象的属性（重要！！！！！）
 * 十六、在运行时获取并调用运行时类对象的方法（重要！！！！！）
 *      1. clazz.newInstance()  默认调用无参构造器创建实例
 *      2. clazz.getConstructor(String.class)  获取有参构造器。创建实例
 *      3. 获取方法、无视访问权限setAccessible(true)等演示效果
 * 特殊：对象的引用（引用传递）
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/28 16:27
 */
public class TestReflection {


    @Test
    public void test1(){
        /**
         * 反射之前：
         * 在编译的时候就能确定创建什么类的对象、操作什么属性、调用什么方法
         */
        Person p = new Person("张三",18);
        System.out.println(p);
    }

    /**
     * 一、获取当前类被那个类加载器加载。ClassLoader
     */
    @Test
    public void test2(){
        /**
         * 获取当前类的 类加载器。AppClassLoader  系统类加载器
         */
      ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println("获取到当前类的类加载器（系统类加载器）："+classLoader);

        /**
         * 获取系统类加载器的父类加载器。ExtClassLoader  拓展类加载器
         */
       ClassLoader classLoader1 = classLoader.getParent();
        System.out.println("获取到当前类的父类加载器（拓展类加载器）："+classLoader1);

        /**
         * 获取拓展类加载器的父类加载器。结果是null。引导类加载器，这个无法直接获取，因为是c++编写的。
         */
       ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println("获取到当前类的父类的父类加载器（引导类加载器）："+classLoader2);

        String className = "java.lang.String";
        try {
            Class clazz = Class.forName(className);
            ClassLoader classLoader3 = clazz.getClassLoader();
            System.out.println("String类是被谁加载的："+classLoader3+"(如果为null，就是引导类加载器，加载的java核心包)");

            Class clazz1 = Class.forName("util.study.reflect.TestReflection");
            ClassLoader classLoader4 = clazz1.getClassLoader();
            System.out.println("util.study.reflect.TestReflection(当前类)是被谁加载的："+classLoader4+"(如果为null，就是引导类加载器，加载的java核心包)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 二、类加载器读取配置文件Properties（对比原始方式）
     */
    @Test
    public void test3(){

        Properties properties = new Properties();
        try {
            /**
             * 原始方式。主要看获取输入节点流的方式。加载“资源路径下”的配置文件
             */
//            properties.load(new FileInputStream("src/main/resource/TestJdbc.properties"));

            /**
             * 类加载器的方式。主要是为了获取出入节点流。 加载“包路径”下的配置文件。
             */
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("util/study/reflect/TestJdbc.properties");
            properties.load(inputStream);
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            System.out.println(username);
            System.out.println(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 三、在运行时创建对象的实例
     *      类名.class方式。省去强转
     *      随便传进来一个类，获取他的类型名
     *
     */
    @Test
    public void test4(){
        /**
         * 通过运行时类的属性 class。 即 类名.class   来获取类的信息
         * 利用泛型，省去强转
         */
        Class<Person> clazz = Person.class;
        try {
            /**
             * 通过反射 调用 newInstance()方法。 通过调用无参构造器，来创建实例
             * 获取Person类的实例
             */
           Person person =  clazz.newInstance();
            System.out.println(person);
            /**
             * 获取这个类的类型. 随便传进来一个类，获取他的类型名
             */
            String type = Person.class.getTypeName();
            System.out.println(type);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 四、获取类的构造器Constructor
     */
    @Test
    public void test5(){
        try {
            /**
             * 通过Class类的静态方法 forName(String className)   来获取类的信息。
             * 优点：直接提供类的全路径名即可
             * 缺点：需要强转
             */
          Class clazz =  Class.forName("util.study.reflect.Person");
//         Person person = (Person) clazz.newInstance();
            /**
             * getConstructors()
             * 获取类的构造器。只能获取public的构造器
             */
           Constructor[] constructors = clazz.getConstructors();
            System.out.println("-----------获取public构造器:getConstructors()---------------------");
           for(int i=0; i<constructors.length;i++){
               System.out.println(constructors[i]);
           }

            System.out.println("-----------获取已声明的构造器:getDeclaredConstructors()---------------------");
            /**
             * getDeclaredConstructors()
             * 获取所有已声明的构造器。包括私有的、默认的、保护的
             */
            Constructor[] constructors2 = clazz.getDeclaredConstructors();
            for(int i=0; i<constructors2.length;i++){
                System.out.println(constructors2[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 五、获取类的属性Field
     */
    @Test
    public void test6(){
        /**
         * 通过运行时类的属性 class。 即 类名.class   来获取类的信息
         * 利用泛型，省去强转
         */
        Class clazz = Person.class;
        /**
         * getFields()
         * 获取类中的所有public属性。包括父类的。顺序是：先本类，然后再父类
         */
        Field[] fields = clazz.getFields();
        System.out.println("-----------获取public属性（包括父类）:getFields()---------------------");
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getName());
        }

        System.out.println("-----------获取已声明的属性（不包括父类）:getDeclaredFields()---------------------");
        /**
         * getDeclaredFields()
         * 获取已声明的所有属性，包括私有的。  不包括父类的
         */
        Field[] fields2 = clazz.getDeclaredFields();
        for(int i=0;i<fields2.length;i++){
            System.out.println(fields2[i].getName());
        }
    }

    /**
     * 六、获取类的方法Method
     */
    @Test
    public void test7(){
        Class clazz = Person.class;
        /**
         * getMethods()
         * 获取所有public修饰的方法，包括父类的
         */
        Method[] methods = clazz.getMethods();
        System.out.println("-----------获取public方法（包括父类）:getMethods()---------------------");
        for(int i=0;i<methods.length;i++){
            System.out.println(methods[i].getName());
        }

        /**
         * getDeclaredMethods()
         * 获取所有声明的方法，包含私有的，但是不包含父类
         */
        Method[] methods1 = clazz.getDeclaredMethods();
        System.out.println("-----------获取所有声明的方法（不包括父类）:getDeclaredMethods()---------------------");
        for(int i=0;i<methods1.length;i++){
            System.out.println(methods1[i].getName());
        }
    }

    /**
     *七、获取运行时类的父类
     */
    @Test
    public void test8(){
        Class clazz = Person.class;
        /**
         * getSuperclass()
         * 获取类的父类。   获取到父类之后，同样也可以用相同的操作获取到Field、Method等
         */
        Class superClass = clazz.getSuperclass();
        System.out.println(superClass);
        System.out.println("-----------------父类的属性Field-----------------------");
        Field[] fields = superClass.getFields();
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getName());
        }
    }

    /**
     * 八、在运行时获取带泛型父类的类型
     * 如： util.study.reflect.Creature<java.lang.String>
     */
    @Test
    public void test9(){
        Class clazz = Person.class;
        /**
         * 获取带泛型的父类类型
         */
        Type type = clazz.getGenericSuperclass();
        System.out.println(type);
    }

    /**
     * 九、在运行时获取带泛型父类他的泛型类型（重要）
     *      如：util.study.reflect.Creature<java.lang.String> 中的 java.lang.String
     */
    @Test
    public void test10(){
        Class clazz = Person.class;
        /**
         * 1.获取带泛型的父类类型
         */
        Type type = clazz.getGenericSuperclass();

        /**
         * 2.参数化。
         * 将type强转成ParameterizedType  参数化类型，例如Collection <String>。
         */
        ParameterizedType parameterizedType = (ParameterizedType) type;
        /**
         * 3.从参数化类型中，返回实际的泛型类型数组
         */
        Type[] types = parameterizedType.getActualTypeArguments();
        for(int i=0;i<types.length;i++){
            System.out.println(types[i]);
        }

        System.out.println("-----------------提取出来泛型类----------------------------");
        /**
         * 4.从泛型类型数组中提取出来 对应的泛型类
         */
        System.out.println(types[0].getTypeName()); // 方式一，Type实例直接获取getTypeName()

        Class clazz1 = (Class) types[0];        // 方式二，把Type强转成Class类型，然后getName()
        System.out.println(clazz1.getName());

    }

    /**
     * 十、在运行时获取运行时的接口
     */
    @Test
    public void test11(){
        Class clazz = Person.class;

        /**
         * 获取类的接口
         */
        Class[] classes = clazz.getInterfaces();
        for(int i=0;i<classes.length;i++){
            System.out.println(classes[i].getName());
        }
    }

    /**
     * 十一、在运行时获取运行时类的内部类
     */
    @Test
    public void test12(){
        Class clazz = Person.class;

        System.out.println("---------------获取public的内部类，包含父类:getClasses()------------------------------");
        /**
         * getClasses()
         * 获取public修饰的内部类。包含父类
         */
        Class[] classes = clazz.getClasses();
        for(int i=0;i<classes.length;i++){
            System.out.println(classes[i].getName());
        }

        System.out.println("---------------获取声明的内部类:getDeclaredClasses()------------------------------");
        /**
         * getDeclaredClasses()
         * 获取所有生命的内部类。包含私有的
         */
        Class[] classes1 = clazz.getDeclaredClasses();
        for(int i=0;i<classes1.length;i++){
            System.out.println(classes1[i].getName());
        }
    }

    /**
     * 十二、在运行时获取运行时类的注解
     */
    @Test
    public void test13(){
       Class clazz = Person.class;
       Annotation[] annotations = clazz.getAnnotations();
       for(int i=0;i<annotations.length;i++){
           System.out.println(annotations[i]);
       }
    }

    /**
     * 十三、在运行时获取运行时类属性的完整结构：修饰符、数据类型、数据名
     */
    @Test
    public void test14(){
        Class clazz = Person.class;
        Field[] fields = clazz.getFields();
        System.out.println("-------------获取修饰符getModifiers()-----------------");
        System.out.println("getModifiers()得到目标属性的修饰符值“之和”(public为1、private为2、protected为4、static为8、final为16)");
        System.out.println("可通过  Modifier.toString(数字和) 转换为修饰符：如public final \n");
        for(int i=0;i<fields.length;i++){
            /**
             * 获取变量名
             */
            String name = fields[i].getName();
            /**
             * getModifiers()
             * 获取修饰符。结果是个 int类型
             *
             * Modifier.toString(int类型) 可以把int类型的修饰符转换为 例如public final等等
             */
            int mod = fields[i].getModifiers();
            String strMod = Modifier.toString(mod);
            /**
             * 获取变量类型。结果是个Class
             *    基本类型：int
             *    包装类型：class java.lang.Integer
             */
            Class type = fields[i].getType();
            String typeName = type.getTypeName();
            System.out.print(mod+" "+type.getName()+" "+name);
            System.out.println(" ---用Modifier类把数字"+mod+"转换后是："+strMod+" "+typeName+" "+name);
        }
    }

    /**
     * 十四、在运行时获取运行时类方法的完整结果
     *      注解 修饰符  返回值类型  方法名（参数类型1 参数名1, 参数类型2 参数名2 .....） 异常
     *      包含注解的两种解析方式
     */
    @Test
    public void test15(){
        Class clazz = Person.class;
//        Method[] methods = clazz.getMethods();
        Method[] methods = clazz.getDeclaredMethods();
        for(int i=0;i<methods.length;i++){
            /**
             * 0.获取方法上的注解
             * getAnnotations()
             */
            Annotation[] annotations = methods[i].getAnnotations();
            for(int k=0;k<annotations.length;k++){
                /**
                 * 0.1 解析注解的方法一：
                 *  循环解析注解。
                 */
//                for(Annotation a : annotations){
//                    if(a instanceof MyAnnotation){
//                        MyAnnotation myAnnotation = (MyAnnotation) a;
//                        System.out.println("注解解析方式一：我的注解值是===》"+myAnnotation.value());
//                    }
//                }
                System.out.print(annotations[k]);
            }

            /**
             * 0.2 第二种解析注解的方式：
             *  先判断方法上是否有我们想要的注解。
             *  如果有，就获取该注解
             */
//            boolean methodHasAnnotation = methods[i].isAnnotationPresent(MyAnnotation.class);
//            if(methodHasAnnotation){
//                MyAnnotation myAnnotation = methods[i].getAnnotation(MyAnnotation.class);
//                System.out.println("注解解析方式二：我的注解值是===》"+myAnnotation.value());
//            }

            /**
             * getModifiers()
             * 1.获取修饰符。结果是个 int
             *
             * Modifier.toString(int类型) 可以把int类型的修饰符转换为 例如public final等等
             */
            int mod = methods[i].getModifiers();
            String strMod = Modifier.toString(mod);
            System.out.print(" "+strMod);
            /**
             * 2.获取返回值类型
             * getReturnType()
             *      基本类型：int
             *      包装类型：class java.lang.Integer
             */
            Class returnType =  methods[i].getReturnType();
            System.out.print(" "+returnType.getName());

            /**
             * 3.获取方法名
             */
            String methodName = methods[i].getName();
            System.out.print(" "+methodName);

            /**
             * getParameterTypes()
             * 4.获取参数列表
             *
             * getParameterCount()
             * 获取参数个数
             */
            System.out.print(" (");
            Class[] paramTypes = methods[i].getParameterTypes();
            int parameterCount = methods[i].getParameterCount();
//            System.out.println(parameterCount);
            for(int j=0;j<paramTypes.length;j++){
                System.out.print(paramTypes[j].getSimpleName());
                if(j+1 < parameterCount){
                    System.out.print(",");
                }
            }
            System.out.print(")");

            /**
             * 5.获取异常信息
             */
            Class[] exceptions = methods[i].getExceptionTypes();
            for(int n=0;n<exceptions.length;n++){
//                System.out.println(exceptions.length);
                System.out.print(" "+exceptions[n].getName());
            }
            System.out.println();
        }
    }

    /**
     * 十五、在运行时获取并调用运行时类对象的属性（重要！！）
     */
    @Test
    public void test16() throws Exception {
        Class clazz = Class.forName("util.study.reflect.Person");

        /**
         * 1.获取对象实例
         *  newInstance()
         */
        Person person1 = (Person) clazz.newInstance();
        /**
         * 2.获取变量
         */
        Field name = clazz.getField("name");
        Field age = clazz.getDeclaredField("age");
        /**
         * 3.给变量赋值。
         *   set(obj,value).
         *      第一个参数传对象实例，表示给那个对象的这个变量赋值
         *      第二个参数传这个变量的值。
         *  注意。变量如果是private修饰的，就必须用该变量调用setAccessible(true)，才可以访问。
         *      public protected 和默认的  都不需要调用就可以访问
         */
        name.set(person1,"张三");
        age.setAccessible(true); // 忽略访问权限（访问private私有变量的的时候需要用）
        age.set(person1,18);
        /**
         * 4.获取变量的值
         */
        String str1 = (String) name.get(person1);
        System.out.println(str1);
        int age1 = (int) age.get(person1);
        System.out.println(age1);
        System.out.println(age.getType().getSimpleName());
    }

    /**
     * 十六、在运行时获取并调用运行时类对象的方法
     *      1. clazz.newInstance()  默认调用无参构造器创建实例
     *      2. clazz.getConstructor(String.class)  获取有参构造器。创建实例
     *      3. 获取方法、无视访问权限setAccessible(true)等演示效果
     */
    @Test
    public void test17() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Person.class;
        /**
         * 1.创建一个对象的实例
         * clazz.newInstance()  调用无参构造器
         */
        Person person1 = (Person) clazz.newInstance();

        /**
         * 1.1 获取 有参的构造器
         *      clazz.getConstructor(String.class)。获取public的构造器,参数列表是构造器的参数列表： 类名.class
         *     clazz.getDeclaredConstructor(String.class)。 可以获取私有的构造器,参数列表是构造器的参数列表： 类名.class
         *          通过有参构造器来创造实例
         */
//        Constructor constructor = clazz.getConstructor(String.class);
        Constructor constructor = clazz.getDeclaredConstructor(String.class); // 获取private私有的构造器
        constructor.setAccessible(true);                                    // 同时需要无视访问权限
        Person person2 = (Person) constructor.newInstance("张三");        //有参构造器创建实例
        Method setAge = clazz.getMethod("setAge", Integer.class);       // 获取方法。
        setAge.invoke(person2,14);                                      //调用方法
        System.out.println("有参构造器创建的对象："+person2);

        /**
         * 2 获取指定的方法
         * getMethod(string , param...)
         *      第一个参数是方法名
         *      第二个可变参数是“该方法的参数列表”,都是类名.class（基本类型也是,如int.class），无参的不填写
         *
         *      注意：这里入参的Double.class 和 double.class是不一样的。
         *            Double.class传的值要是带小数位。如100.0    传100就不行
         *            double.class可以传100  也可以传100.0
         */
        Method eat = clazz.getMethod("eat");

        /**
         * 3.调用方法。
         *  通过 方法.invoke(obj，param...)
         *      第一个参数：对象实例。 表示调用哪一个对象的该方法
         *      第二个参数：可变参数。表示这个方法的实际参数列表（实参）
         *      invoke方法的返回值，就是实际方法的返回值。
         */
        eat.invoke(person1);

        /**
         * 2.1 获取指定的方法
         * getMethod(string , param...)
         *      第一个参数是方法名
         *      第二个可变参数是“该方法的参数列表”,都是类名.class（基本类型也是,如int.class），无参的不填写
         *
         *      注意：这里入参的Double.class 和 double.class是不一样的。
         *            Double.class传的值要是带小数位。如100.0    传100就不行
         *            double.class可以传100  也可以传100.0
         */
        Method setName = clazz.getMethod("setName",String.class,double.class,int.class);
        setName.invoke(person1,"张三",100.0,25);

        /**
         * 2.2 访问私有方法。
         *      1.需要用getDeclaredMethod("方法名",参数类型列表...)，来获取私有方法
         *      2.还要设置无视访问权限。 方法名.setAccessible(true);
         */
        Method wangToFly = clazz.getDeclaredMethod("wangToFly"); //获取私有方法
        wangToFly.setAccessible(true); //无视访问权限
        /**
         * invoke方法的返回值，就是实际方法的返回值。
         */
        String strReturn = (String) wangToFly.invoke(person1);
        System.out.println(strReturn);
    }

    /**
     * 特殊：对象的引用（引用传递）
     * 测试引用传递
     */
    @Test
    public void Person(){
        /**
         * 一个变量 person 在栈中，
         * 一个对象 new Person()在堆中
         *      栈中的person变量里面保存的是 new Person()对象在堆中的地址。
         */
        Person person = new Person();
        Map map = new HashMap();
        map.put("k1","123");

        /**  （map也是同理）。  对象的引用（引用传递）
         * person变量（在栈中）保存的new Person对象（在堆中）的地址（一串地址：00000012）
         * 此时方法这里的person传递的其实是栈中的person变量的一个副本p，p同样保存的一串地址 0000012。
         *  也就是说栈中其实现在有两个变量 person 和 p 都保存的new Person对象的地址（一串地址：0000012）
         *
         *
         *      然后在方法里面testPerson(person,map)：
         *      1.如果 p.name(name是public的） = “张三”， 或者  p.setName("张三)，那么p所指的对象（new Person）的内容会改变，且会影响到外面。
         *          因为是对象在调用内部的public属性或者方法,来修改该对象的值。堆中这个对象的属性被改了，方法内外都会影响到。这个时候不管你用哪个
         *          变量去引用这个new Person对象（0000012），它得出来的值都是修改过的。
         *      2.但是如果这时操作是： p = null 的话， 表示的是把p变量所指的那一串new Person对象的地址（0000012）换成了null，
         *          这时候只是将栈中的一个变量副本p中保存的地址（0000012）给清空了，这时候new Person对象（0000012）还在，只不过通过p变量
         *          找不到了而已，通过栈中的另一个person变量还是可以找到new Person对象（0000012）的。
         */
        testPerson(person,map);
        System.out.println("方法外部的person："+person);
        System.out.println("方法外部的map"+map);
    }

    @Test
    public void testPerson(Person p,Map map){
        p = null;
        map.put("k1","456");
        System.out.println("方法内部的p:："+p);
        System.out.println("方法内部的map"+map);
    }

}
