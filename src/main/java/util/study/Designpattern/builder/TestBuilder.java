package util.study.Designpattern.builder;

/**
 * 【建造者模式】
 *      测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/14 15:57
 * @File : TestBuilder
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestBuilder {
    public static void main(String[] args) {
        /**
         * 1.创建一个java建造者
         *      建造者实例创建的时候，里面就会创建一个“被创建者的实例，也就是课程实例”。因为课程就是该建造者的一个成员变量。
         */
        IBuilder builder = new JavaBuilder();

        /**
         * 2. java建造者，使用“链式写法”来设置java课程的属性。
         */
        builder.addCourse("java设计模式")
                .addVideo("java视频")
                .addNote("java笔记")
                .addPpt("javaPPT");
        /**
         * 3. java建造者调用builder来创建一个java课程对象
         */
        ICourse javaCourse = builder.build();
        System.out.println(javaCourse);
        // 每个建造者操作的的都是同一个对象实例：这里建造者操作的还是同一个实例。
        //      因此下面的操作等同于 javaCourse.setCourseName("python");

        // 若想要操作不同的实例，那就得再创建一个建造者了。
        builder.addCourse("python");    // 等同于 javaCourse.setCourseName("python");
        System.out.println(javaCourse);
    }
}
