package util.io.before;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/19 13:09
 * @File : TestConsoleIo
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 控制台I0 :
 * System.in :“标准”的输入流。   标准输入流不用关闭，单例的。整个过程中只有一个。关闭之后就获取不到了
 * System.out : “标准”的输出流
 * System.err : “标准”的错误输出流
 *
 * 打印流：PrintStream  和  PrintWriter
 *
 * 【重要】
 * 转换流：也是处理流中的一种。将 "字节流" 转换成 "字符流"。    字节流===》字符流
 * InputStreamReader   OutputStreamWriter
 *      编码：字符串 =》 字节数组
 *      解码：字节数组 =》 字符串
 *
 * 一、修改标出输出的位置
 * 二、将异常信息打印文件里面
 * 三、从控制台获取获取输入流
 * 四、转换流：字节输入流转换为字符输入流，获取标准输入流
 * 五、转换流：字节输出流转换为字符输出流。编码后写入磁盘中
 * 六、转换流：字节输入流转换为字符输入流。从磁盘中解码取出，打印在控制台
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/19 13:09
 */
public class TestConsoleIo {
    /**
     * 一、修改标出输出的位置
     */
    @Test
    public void test1(){
        System.out.println("标准输出流");
        System.err.println("标准错误输出流");

        try {
            /**
             * 改变这个标准输出流的位置。默认是控制台，现在改成到某个文件
             */
            System.setOut(new PrintStream(new FileOutputStream("src/main/resource/io/ConsoleIo/out")));
            /**
             * 改变这个标准错误流的位置。默认是控制台，现在改成到某个文件
             */
            System.setErr(new PrintStream(new FileOutputStream("src/main/resource/io/ConsoleIo/err")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * 这两句不会出现在控制台，而是出现在各自指定的文件里
         */
        System.out.println("我是改变后的输出流");
        System.err.println("我是改变后的错误流");
    }

    /**
     * 二、将异常信息打印文件里面
     */
    @Test
    public void test2(){
        try {
            int div = 10/0;
        } catch (Exception e) {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter("src/main/resource/io/ConsoleIo/exception"));
                e.printStackTrace(pw);
                pw.flush();     // 不刷新的话，这里的字符流就写不进文件中。 构造函数中不在刷新
                pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 三、从控制台获取获取输入流。
     *     idea请通过main方法来输入。因为测试的时候控制台是只读的
     *   标准输入流不用关闭，单例的。整个过程中只有一个。关闭之后就获取不到了。
     *
     */
    public  void test3(String[] args) {
        InputStream in = null ;
        /**
         * 1.从控制台获取输入流
         */
        try {
            in = System.in;
            /**
             * 2.创建字节数组
             */
            byte[] bytes = new byte[1024];
            int len = 0;
            /**
             * 3.从输入流中读取内容（这里就是从键盘读取）
             */
            while( (len = in.read(bytes)) != -1){
                System.out.println("打印结果："+new String(bytes,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 四、转换流：字节输入流转换为字符输入流，获取标准输入流
     *      字节转换为字符：解码
     *  idea通过main方法来输入。因为测试的时候控制台是只读的
     */
    @Test
    public void test4(){
        try {
            /**
             * 1.获取标准输入流，这是个字节流
             * 标准输入流不需要关闭。单例的。整个过程中只有一个。关闭之后就获取不到了。
             */
            InputStream in = System.in;
            /**
             * 2.用转换流，将标准输入流（现在是字节流）转换为转换为字符流。
             *      字节转换为字符：解码
             */
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            /**
             * 3.用字符缓冲流包转一下转换后的字符流，用来提高效率
             */
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            /**
             * 4.读取内容,字符这里采用一次读一行
             */
            String str = null;
            while( (str = bufferedReader.readLine()) != null){
                System.out.println("打印结果："+str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 五、转换流：字节输出流转换为字符输出流。编码后写入磁盘中
     */
    @Test
    public void test5(){
        BufferedWriter bufferedWriter = null;
        try {
            /**
             * 1.创建一个字节输出节点流。打开通道
             */
            OutputStream outputStream = new FileOutputStream("src/main/resource/io/ConsoleIo/outputStreamWriter");
            /**
             * 2.创建一个转换流，来包装一下节点流。 把字节输出流转换为字符输出流
             * 可以使用默认的编码方式，也可以自己指定编码方式
             */
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
            /**
             * 3.创建一个字符缓冲流，来包装一下字符输出流。 提高效率
             */
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            /**
             * 4.往目标文件写入数据
             */
            bufferedWriter.write("这是我测试‘转换流’：字节流输出流转换为字符输出流");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /**
             * 5.关闭流。只需要关闭最后一个处理流就可以了。他会关闭前面所有的，包括节点流
             */
            try {
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 六、转换流：字节输入流转换为字符输入流。从磁盘中解码取出，打印在控制台
     */
    @Test
    public void test6(){
        BufferedReader bufferedReader = null;
        try {
            /**
             * 1.创建输入节点流对象
             */
            InputStream inputStream = new FileInputStream("src/main/resource/io/ConsoleIo/outputStreamWriter");
            /**
             * 2.创建转换流对象,包装输入节点流。将字节输入流转换成字符输入流
             * 可以使用默认的编码方式来解码。也可以自己指定编码方式来解码，但是指定的时候，需要使用和编码时的一样才可以解出来
             *      比如：编码时用的是utf-8,解码时用的却是gbk，此时就会出现乱码
             */
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf8");  // 这里写utf8 和 utf-8是一样的
            /**
             * 3.创建字符缓冲流，包装输入字符流，来提高效率
             */
            bufferedReader = new BufferedReader(inputStreamReader);
            /**
             * 4.读取数据内容，字符这里采用一次读100个字符
             */
            char[] chars = new char[10];
            int len = 0;
            while( (len = bufferedReader.read(chars)) != -1){
                /**
                 * 将字符数组转换为字符串，从0开始，转换实际读取的字符长度
                 */
                System.out.print(new String(chars,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 5.关闭流
             *  只需要关闭最后一个处理流即可。他会关闭前面所有的，包括节点流
             */
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        TestConsoleIo testConsoleIo = new TestConsoleIo();
        testConsoleIo.test4();
    }
}
