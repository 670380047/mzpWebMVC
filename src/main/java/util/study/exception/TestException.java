package util.study.exception;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 11:10
 * @File : TestException
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 一、异常:不可预知的非正常情况。
 *      Java中异常都是以对象形式存在的，一旦某句代码发生异常， 会在该代码处
 *      生成一个异常对象,然后以堆栈式抛出，若不对该异常对象进行处理，最终导致程序终止运行
 * 二、异常的结构体系
 *      java.1ang.Throwable :所有错误和异常的父类
 *          -- java.lang.Error :错误，表示一些严重的错误。如:内存溢出、系统错误等。因此我们在程序中不对其进行处理
 *          -- java.lang.Exception :异常， 我们应该尽可能的预知并处理的异常。如:用户输入错误、 网络连接中断等等。
 *              --编译时异常(受检异常checked) :编译时对其进行检查,若不对其进行处理，编译不能通过。
 *              --运行时异常(非受检异常unchecked) :可以保证程序的正常运行,但是一旦发生异常,会在该代码出生成一个 异常对象然后
 *                  抛出，若不对其进行处理,最终程序终止运行。  比如空指针、数组越界、算术异常（除以0）、类型转换异常、
 *              --自定义异常时的选择：(当继承RuntimeException说明是运行时异常，继承Exception是编译时异常)。
 *                  选择编译时异常的情况：因为编译的时候就可以看到，方便团队开发时别人调用我的方法时，给别人看的。
 *                      throw一个编译时异常后，还需要throws出去才行。 运行时异常就不用throws了。
 *                  选择运行时异常，自己制造（throw）一个异常，别人不会调用我的方法。而且我也省得一层一层的throws。
 *
 *
 * 三、 throws和throw的区别：其实throws和throw就是名字长得有点像，但是实际作用就不会一回事。
 *          1.throws是异常处理的方式之一，另一种方式是try..catch。
 *              try..catch是在方法内部自己处理了，而throws是把异常信息抛出去，让那个方法调用者去处理。
 *          2.throw是“制造一个异常”。可以人为的 new 一个异常对象出来。要么自己内部处理（用try...catch。不过哪有自己定义异常，完了之后还自己再处理一下，重复做功。一般都是自己定义异常，让别人处理，选择throws）,
 *              要么抛出去让方法调用者去处理（throws）。
 *              其中throw可以替代return关键字。带返回值的方法如果throw一个异常的话，就不需要返回值了。
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 11:10
 */
public class TestException {
}
