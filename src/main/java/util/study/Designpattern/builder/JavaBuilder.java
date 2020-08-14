package util.study.Designpattern.builder;

/**
 * 【建造者模式】
 *      建造者的实现类：java课程建造者。构建java课程有许多方法，但是他们的执行顺序不固定，谁先谁后都可以。
 *      采用“链式写法”，即每一个建造动作都返回当前建造者this：以便调用的时候可以链式调用。
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/14 15:43
 * @File : JavaBuilder
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaBuilder implements IBuilder{

    /**
     * 1.创建一个java课程的实现类
     */
    ICourse course = new JavaCourse();


    /**
     * 2.这是一个添加课程名的方法，里面再调用java课程的“设置课程名”的方法。
     *      返回一个当前建造者this。这是链式写法，外面调用的时候可以一直链式写下去。
     * @param name
     * @return
     */
    @Override
    public IBuilder addCourse(String name) {
        // 调用java课程的方法，来设置课程的名字
        course.setCourseName(name);
        // 链式写法。返回调用者本身
        return this;
    }

    @Override
    public IBuilder addVideo(String video) {
        course.setVideo(video);
        return this;
    }

    @Override
    public IBuilder addNote(String note) {
        course.setNote(note);
        return this;
    }

    @Override
    public IBuilder addPpt(String ppt) {
        course.setPpt(ppt);
        return this;
    }

    /**
     * 3.这个方法就是来构建一个java课程对象的。
     *      当需要的课程属性设置完之后，就可以调用这个方法来获取这个课程。
     * @return
     */
    @Override
    public ICourse build() {
        // ...省略对这个对象的构建过程的处理逻辑。
        // 返回最后的对象
        return course;
    }
}
