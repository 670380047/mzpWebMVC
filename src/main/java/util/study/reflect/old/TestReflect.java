package util.study.reflect.old;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/26 9:37
 * @File : ReflectField
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class TestReflect {
    public static void main(String[] args){
        fieldTest();
//        methodTest();
    }

    /**
     * 反射测试。field属性测试
     */
    public static void fieldTest(){
        //通过反射机制获取FieldBeReflected的class对象
        Class mClazz = FieldBeReflected.class;

        //获取该对象继承的父对象
        Class superClass = mClazz.getSuperclass();
        System.out.println("FieldBeReflected的父类："+superClass);
        //获取包对象。
        Package packageName = mClazz.getPackage();
        System.out.println("包名："+packageName);
        String className = mClazz.getName();
        System.out.println("包名.类名："+className);

        try {
            Field fName = mClazz.getDeclaredField("name");
            Field fBoolean = mClazz.getDeclaredField("mBoolean");
            Field fByte = mClazz.getDeclaredField("mByte");
            Field fShort = mClazz.getDeclaredField("mShort");
            Field fInt = mClazz.getDeclaredField("mInt");
            Field fLong = mClazz.getDeclaredField("mLong");
            Field fFloat = mClazz.getDeclaredField("mFloat");
            Field fDouble = mClazz.getDeclaredField("mDouble");
            Field fChar = mClazz.getDeclaredField("mChar");

            /*声明为true，即使是private修饰的，也可以访问。
            * 为false时，被反射类和反射类在同一个包下的时候，只有private不可访问
            *           如果不在同一个下的时候，则protect和private均不可访问。
            */
            fName.setAccessible(true);
            //因为目标属性(FieldBeReflected中的name属性)是静态的，所以set时第一个参数传类，或者传类的对象mClazz.newInstance()都可以
            //get时传入的参数也只能是类，不能是类的对象。

            //得到目标属性的修饰符值(public为1、private为2、protected为4、static为8、final为16)
            //getModifiers()得到的是所有修饰符的求和。
            fName.set(mClazz.newInstance(),"毛哥");
            System.out.println("name："+fName.getName()+"  value:"+fName.get(mClazz)
                    +"  Type:"+fName.getGenericType().getTypeName()
                    +"  modifier:"+fName.getModifiers());

            //得到目标属性对应的Class对象
            Class clazz1 = fName.getType();
            System.out.println("clazz1:"+clazz1.getName());
            //得到目标属性所在类对应的Class对象
            Class clazz2 = fName.getDeclaringClass();
            System.out.println("clazz2:"+clazz2+"=====>"+clazz2.getName().equals(mClazz.getName()));



            fBoolean.setAccessible(true);
            /*得到目标属性的布尔值，因为目标属性在定义时是非静态的，所以此处传入
             *的实参为目标属性所在类的实例mClazz.newInstance(). 注意！这里设置和取值传入的实例要是同一个实例
             */
            //生成一个实例
            FieldBeReflected fieldBeReflected = (FieldBeReflected) mClazz.newInstance();
            System.out.println("由反射CLASS产生的实例,还可以通过getClass继续获得Class对象，然后继续其他的操作："+fieldBeReflected.getClass().getDeclaredField("mChar").get(fieldBeReflected.getClass()));
            fBoolean.set(fieldBeReflected,true);
            boolean mBoolean = fBoolean.getBoolean(fieldBeReflected);
            System.out.println("name："+fBoolean.getName()+"  value:"+mBoolean);

            fByte.setAccessible(true);
            //final修饰的变量，不能修改值
            //fByte.set(fieldBeReflected,777);
            Byte mByte = (Byte) fByte.get(fieldBeReflected);
            System.out.println("name:"+fByte.getName()+"  value:"+mByte
                    +"  Type:"+fByte.getGenericType().getTypeName()
                    +"  modifier:"+fByte.getModifiers());

            fInt.setAccessible(true);
            fInt.set(mClazz,83510);
            int mInt = (int) fInt.get(mClazz);
            System.out.println("name:"+fInt.getName()+"  value:"+mInt
                    +"  Type:"+fInt.getGenericType().getTypeName()
                    +"  modifier:"+fInt.getModifiers());

            Field[] fields = mClazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                System.out.println("field数组："+fields[i].getName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射测试。method方法测试
     */
    public static void methodTest(){

        //通过反射获取对象的Class
        Class<?> mClazz = MethodBeReflected.class;
        //这两种方式获取的Class对象是一样的。
        System.out.println(mClazz== new MethodBeReflected().getClass());
        try {
            //第一个参数为目标方法的名字，第二个参数为目标方法的形参（这里是可变参数，可以有多个）
            Method nameMethod = mClazz.getDeclaredMethod("setmName", String.class);

            /*声明为true，目标方法即使是private修饰的，也可以访问。
             * 为false时，被反射类和反射类在同一个包下的时候，只有private不可访问
             *           如果不在同一个下的时候，则protect和private均不可访问。
             */
            nameMethod.setAccessible(true);
            System.out.println("name:"+nameMethod.getName()+"  value:"+nameMethod.getDefaultValue());
            MethodBeReflected methodBeReflected = (MethodBeReflected) mClazz.newInstance();
            //通过目标方法通过调用invoke()方法来调用间接调用目标方法本身。
            //因为setmName方法是静态方法，所以第一个参数可以输Class对象（mClazz），也可以是Class对象的实例（newInstance）
            //          后面的参数依次是目标方法的形参。
            nameMethod.invoke(methodBeReflected,"小毛");
            System.out.println("name:"+nameMethod.getName()+"  value:"+nameMethod.getDefaultValue());

            //对于没有形参的目标方法，获取方法的时候，第二个可变参数可以不写，或者写null
            //nameMethod = mClazz.getDeclaredMethod("getmName");
            nameMethod = mClazz.getDeclaredMethod("getmName",null);
            //一个nameMethod变量用了两次，分别接受了getmName和setmName。  注意！要注意设置可访问性nameMethod.setAccessible(true)
            nameMethod.setAccessible(true);
            //对于没有形参的目标方法，调用invoke（）的时候，第二个可变参数可以不写，或者写null
            String name = (String) nameMethod.invoke(methodBeReflected,null);
            System.out.println("name:"+nameMethod.getName()+"  value:"+name);


            Method ageMethod = mClazz.getDeclaredMethod("setmAge", int.class);
            ageMethod.setAccessible(true);
            ageMethod.invoke(methodBeReflected,24);
            //对于没有形参的目标方法，获取方法的时候，第二个可变参数可以不写，或者写null
            //ageMethod = mClazz.getDeclaredMethod("getmAge");
            ageMethod = mClazz.getDeclaredMethod("getmAge",null);
            ageMethod.setAccessible(true);
            //对于没有形参的目标方法，调用invoke（）的时候，第二个可变参数可以不写，或者写null
            int age = (int) ageMethod.invoke(methodBeReflected);
            //int age = (int) ageMethod.invoke(methodBeReflected,null);
            System.out.println("name:"+ageMethod.getName()+"  value:"+age
                    +" defaultValue:"+ageMethod.getDefaultValue());


            Method weightMethod = mClazz.getDeclaredMethod("setmWeight",float.class);
            weightMethod.setAccessible(true);
            weightMethod.invoke(methodBeReflected,58.2f);
            weightMethod = mClazz.getDeclaredMethod("getmWeight");
            weightMethod.setAccessible(true);
            System.out.println("name:"+weightMethod.getName()+"  value:"+weightMethod.invoke(methodBeReflected)+
                    "  Type:"+weightMethod.toGenericString());


            Method allValuesMethod = mClazz.getDeclaredMethod("setAllValues", String.class, int.class, float.class, String.class);
            allValuesMethod.setAccessible(true);
            allValuesMethod.invoke(methodBeReflected,"小李",25,44.6f,"女");
            nameMethod = mClazz.getDeclaredMethod("getmName");
            nameMethod.setAccessible(true);
            String mName =  nameMethod.invoke(methodBeReflected).toString();
            ageMethod = mClazz.getDeclaredMethod("getmAge");
            ageMethod.setAccessible(true);
            int mAge = (int) ageMethod.invoke(methodBeReflected);
            weightMethod = mClazz.getDeclaredMethod("getmWeight");
            weightMethod.setAccessible(true);
            float mWeight = (float) weightMethod.invoke(methodBeReflected);
            Method sexMethod = mClazz.getDeclaredMethod("getmSex");
            sexMethod.setAccessible(true);
            String mSex = (String) sexMethod.invoke(methodBeReflected);
            System.out.println("姓名："+mName+"  性别："+mSex+"  年龄："+mAge+"  体重："+mWeight);

            //得到目标类所在类对应的Class对象
            Class clazz1 = weightMethod.getDeclaringClass();
            System.out.println(weightMethod.getName()+"方法所在类对应的Class对象："+clazz1.getName());
            //得到目标方法返回对应的Class对象
            Class[] clazzs1 = weightMethod.getExceptionTypes();
            for(Class c1 : clazzs1){
                System.out.println("测试："+c1);
            }
            //得到目标方法抛出的异常类型对应的Type对象
            Type[] types1 = weightMethod.getGenericExceptionTypes();
            System.out.print(weightMethod.getName()+"异常类型对应的Type对象：");
            for (int i = 0; i <types1.length; i++) {
                System.out.print(types1[0]);
            }
            System.out.println();

            //得到目标方法返回类型对应的Type对象
            Type type = nameMethod.getGenericReturnType();
            System.out.println(nameMethod.getName()+"返回类型对应的Type对象："+type);
            //得到目标方法各参数类型对应的Class对象
            Class[] clazzs2 = allValuesMethod.getParameterTypes();
            System.out.print(allValuesMethod.getName()+"方法对应的形参类型对应的Class对象： ");
            for (int i = 0; i < clazzs2.length; i++) {
                System.out.print(clazzs2[i].getName()+"  ");
            }
            System.out.println();

            //得到目标方法各参数类型对应的Type对象
            Type[] types2 = allValuesMethod.getParameterTypes();
            System.out.print(allValuesMethod.getName()+"方法各参数类型对应的Type对象： ");
            for (int i = 0; i < types2.length; i++) {
                System.out.print(types2[i].getTypeName()+"  ");
            }
            System.out.println();

            //得到目标方法修饰符的值
            int modifier = ageMethod.getModifiers();
            System.out.println("目标方法"+ageMethod.getName()+"访问修饰符的值："+modifier
                    +"  访问修饰的值："+ageMethod.toGenericString());
            System.out.println(nameMethod.isVarArgs());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
