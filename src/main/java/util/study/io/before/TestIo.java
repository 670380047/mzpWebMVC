package util.study.io.before;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/14 16:38
 * @File : TestIo
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、IO流的分类：
 *      1.根据流向不同：输入流、输出流。 （流向是以程序为主体：输入流-比如从文件向内存里面输入；   输出流-比如从内存往文件里面输出）。
 *          InputStream 是字节“输入流”  OutputStream是字节“输出流”
 *          Reader 是字符“输入流”         Writer是字符“输入流”
 *      2.根据传输数据类型不同： 字节流（8 bit:8位，一个字节）、字符流(16 bit：16位，两个字节)。 一个字符等于两个字节
 *          InputStream 是“字节”输入流  OutputStream是“字节”输出流
 *          Reader 是“字符”输入流         Writer是“字符”输入流
 *          （字节流用于操作非文本文件，如：.avi .mp3 .jpg）
 *          （字符流用于操作文本文件，如：.java .txt）
 *      3.按角色不同：节点流、处理流
 *          “节点流” 用来打通通道。节点流只有四个。FileInputStream、FileOutputStream、 FileReader、FileWriter
 *          “处理流” 用来包装节点流。处理流有很多个。来实现某些功能：比如缓冲流提高效率、对象流进行序列化、转换流、打印流等
 *
 * 二、IO流的结构体系（竖着看）
 *      只要涉及数据传输，就要创建节点流,节点流有：访问文件的、访问数组的、访问管道的。  节点流可以被多种处理流包装
 *  抽象基类            节点流                 缓冲流(处理流中的一种)
 *  InputStream         FileInputStream     BufferedInputStream
 *  OutputStream        FileOutputStream    BufferedOutputStream
 *  Reader              FileReader          BufferedReader  （readLine可以读一行）
 *  Writer              FileWriter          BufferedWriter   （newLine提供换行）
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/14 16:38
 */
public class TestIo {

    //=====================节点流 start=========================
    /**
     * FileInputStream  （有问题的代码，读取文本文件正确的是用字符流。Reader)
     * 字节流读取文本文件的乱码现象。
     */
    @Test
    public void test1(){
        /**
         * 如果操作文本文件，要用字符流：Reader  Writer.  如果用字节流会出现中文乱码
         *      因为字节流是8bit（一个字节）。
         * 1.创建管道：
         *  创建 FileInputStream 的对象，同时打开指定文件（字节流操作文本文件的话，会产生乱码。所以用字符流）
         */
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/main/resource/io/ioInputTest");
            /**
             * 2. 读取指定文件中的内容
             *  read()是一次读取一个字节。返回的是下一个字节。如果到了末尾就返回-1.
             *      （第一个位置不是第一个字节，而是在第一个字节之前。他的下一位才是第一个字节）
             */
            int num = fileInputStream.read();
            System.out.println(num);
            while(num != -1){
                System.out.println((char)num);
                num = fileInputStream.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                /**
                 * 3.关闭流。 如果不关闭，它将一直占据资源
                 */
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * FileInputStream  （有问题的代码，仅供演示，读取文本文件正确的是用字符流。Reader)
     * 字节流读取文本文件的乱码现象。  字节流操作的缓冲区是byte，字符流是char
     */
    @Test
    public void test2(){
        /**
         * 如果操作文本文件，要用字符流：Reader  Writer.  如果用字节流会出现中文乱码
         *      因为字节流是8bit（一个字节）。
         * 1.创建管道：
         *  创建 FileInputStream 的对象，同时打开指定文件（字节流操作文本文件的话，会产生乱码。所以用字符流）
         */
        FileInputStream fileInputStream = null;
        try {
//            fileInputStream = new FileInputStream("src/main/resource/textBranch");
            fileInputStream = new FileInputStream("src/main/resource/io/ioInputTest");
            /**
             * 2.读取文件内容.
             *  read(byte[])  这里的参数 是字节数组，作为读取数据的缓冲区，从文件中读取的内容就是放在 byte[]缓冲区中，而此时的返回值是本次读取的字节个数。
             *  如果到达文件末尾，返回的是 -1。
             */
            byte[] b = new byte[3];  //创建读取数据的缓冲区,每次读3个字节
            int len = 0;
            while(   (len = fileInputStream.read(b))  != -1){
                /**
                 * 字节数组转换为字符串。
                 *  将字节数组b从0开始转换，每次转换len的长度。例如防止出现读取 123ab  当缓冲区为byte[3]时 ，
                 *  读取的结果为：123   ab3    第二次读取多了一个3，实际第二次只读出来两个字节，替换掉了缓冲区
                 *  中的前两个字节，但是第三个字节“3”还在，然后转换为字符串的时候把这个“3”也转换出来了。
                 *  解决办法：从0开始转换，实际读了几个字节，就转换几个字节
                 */
                System.out.println(new String(b,0,len));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                /**
                 * 3.关闭流。 如果不关闭，它将一直占据资源
                 */
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * FileReader
     * 文件字符输出流。 字符流操作的缓冲区是 char (字节流是byte)
     */
    @Test
    public void testReader(){
        FileReader fileReader = null;
        try {
            /** 创建管道
             * 1.创建字符节点流
             */
            fileReader = new FileReader("src/main/resource/textBranch");
            /**
             * 2.读取文件内容.
             *  read(char[])  这里的参数 是字符数组，作为读取数据的缓冲区，从文件中读取的内容就是放在 char[]缓冲区中，而此时的返回值是本次读取的字符个数。
             *  如果到达文件末尾，返回的是 -1。
             */
            char[] chars = new char[3]; //创建字符数组做为缓冲区。
            int len = 0;
            while( (len = fileReader.read(chars)) != -1 ){
                /**
                 * 字节数组转换为字符串。
                 *  将字节数组b从0开始转换，每次转换len的长度。例如防止出现读取 123ab  当缓冲区为char[3]时 ，
                 *      读取预期结果为： 123   ab  但实际结果为：123   ab3  多了一个3。
                 *  原因：第二次读取多了一个3，实际第二次只读出来两个字节，替换掉了缓冲区
                 *      中的前两个字节，但是第三个字节“3”还在，然后转换为字符串的时候把这个“3”也转换出来了。
                 *  解决办法：从0开始转换，实际读了几个字节，就转换几个字节
                 */
                System.out.print(new String(chars,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /**
             * 3.关闭字符输出流
             */
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileOutputStream （有问题的代码，仅供演示。写文本文件正确的是用字符流。Writer)
     * 字节输出流。
     */
    @Test
    public void test3(){
        String str = "abc123,字节输出流";
        /**
         * 1.创建FileOutputStream对象，同时打开指定文件
         */
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("src/main/resource/ioOutputTest");
            /**
             * 2.将指定内容写到目标文件中。
             */
            fileOutputStream.write(str.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /**
             * 关闭流
             */
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * FileWriter
     * 字符输出流
     */
    @Test
    public void testWriter(){
        String str = "abc123,字符输出流";
        FileWriter fileWriter = null;
        try {
            /**
             * 1.创建FileWriter 对象，并打开指定文件
             */
            fileWriter = new FileWriter("ioWriterTest");
            /**
             * 2.将指定内容写到目标文件中。
             */
            fileWriter.write(str.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /**
             * 关闭流
             */
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 非文本你文件的复制。 调用myCopy
     */
    @Test
    public void copyTest(){
        String src = "src/main/resource/io/imgTest/waterfall.jpg";
        String dest = "src/main/resource/io/imgTest/waterfall(副本).jpg";
        myNoTextCopy(src,dest);

        String textSrc = "src/main/resource/textBranch";
        String testDest = "src/main/resource/textBranch(副本)";
        myTextCopy(textSrc,testDest);
    }

    /**
     * 非文本文件的复制。 使用 FileInputStream 、 FileOutputStream
     */
    @Test
    public void myNoTextCopy(String src,String dest){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            /**
             * 1. 创建FileInputStream 对象，同时打开指定文件
             */
            fileInputStream = new FileInputStream(src);
            /**
             * 2.创建FileOutputStream 对象，同时打开指定文件
             */
            fileOutputStream = new FileOutputStream(dest);
            /**
             * 3.读取指定文件的内容
             */
            byte[] b = new byte[1024];
            int len;
            while( (len = fileInputStream.read(b)) !=-1 ){
                /**
                 * 4.将内容写到目标地点
                 * 读多少个，就写多少个。
                 */
                fileOutputStream.write(b,0,len);
            }
            System.out.println("非文本文件复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 5.关闭流
             */
            try {
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文本文件的复制。 使用FileReader 、 FileWriter
     * @param src
     * @param dest
     */
    @Test
    public void myTextCopy(String src, String dest){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            /**
             * 1.创建FileReader对象，同时打开指定文件
             */
            fileReader = new FileReader(src);

            /**
             * 2.创建FileWriter对象，同时打开指定文件
             */
            fileWriter = new FileWriter(dest);
            /**
             * 3.读取指定文件的内容
             */
            char[] chars = new char[1024];
            int len = 0;
            while( (len = fileReader.read(chars))!=-1 ){
                /**
                 * 4.将内容写到目标地点
                 * 缓冲区读多少个，就写多少个。
                 */
                fileWriter.write(chars,0,len);
            }
            System.out.println("文本文件复制完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                /**
                 * 5.关闭流
                 */
                if(fileReader != null){
                    fileReader.close();
                }
                if(fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 题目：读取文件，并在每行加上行号。
     * 回车换行处理。
     * 在windows操作系统中。所有文本文件每行结尾都有两个不可见的特殊字符表示该行结束。\r\n
     * 提示:可将读出的char数组转换为StringBuilder,然后在字符串中搜索“\n”,并在其之后插入行号即可。
     */
    @Test
    public void newLineNumTest(){
        String src = "H:\\IDEA\\workSpace\\mzpWebMVC\\mzpspringMVC\\src\\main\\java\\util\\io\\before\\TestIo.java";
        FileReader  fileReader = null;
        try {
            fileReader = new FileReader(src);
            /**
             * 每次读100个字符。 一个字符等于两个字节byte
             */
            char[] chars = new char[100];
            int len = 0; // 读取的缓冲区中的字符个数。 如果到了末尾，就为-1
            StringBuilder sb = new StringBuilder();
            int index = 0;  // 记录换行符的索引值
            int fromIndex = 0; // 记录从何处开始查找换行符
            int lineNum = 0; // 记录行号
            /**
             * 记录行号
             * 定义原子变量。构造函数，从1开始做自增。做自增操作时，线程安全
             */
            AtomicInteger atomicInteger = new AtomicInteger(1);
            while( (len = fileReader.read(chars)) != -1){
                // 将字符数组变为字符串，然后存入StringBuilder中
                String str = new String(chars,0,len);
                sb.append(str);
                /**
                 * 1.依次从fromIndex位置开始查找 \n 换行符。
                 * 2.并且给他的下一位插入一个自增的行号。
                 * 3.修改下一次查抄换行符的位置。fromIndex
                 */
                while( (index = sb.indexOf("\n",fromIndex)) != -1){
                    lineNum = atomicInteger.getAndIncrement();
                    sb.insert(index+1,lineNum+".");
                    fromIndex = index + 1;
                }
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 关闭流
             */
            if(fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //=====================节点流 end=========================

    //=====================缓冲流 start=========================

    /**
     * 处理流。
     * 用来包装一下节点流，提高传输效率
     */
    @Test
    public void bufferedTest(){
        BufferedInputStream bufferedInputStream = null;
        try {
            /**
             * 1.创建节点流对象
             */
            FileInputStream fileInputStream = new FileInputStream("src/main/resource/textBranch");
            /**
             * 2.创建缓冲流对象，包装现有的节点流
             */
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            /**
             * 3.读取指定文件的内容
             */
            byte[] bytes = new byte[1024];
            int len = 0;
            while( (len = bufferedInputStream.read(bytes)) != -1){
                System.out.print(new String(bytes,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 关闭缓冲流即可，缓冲流会自动关闭节点流
             *  只需要关闭最后一个处理流就可以了。他会关闭前面所有的，包括节点流
             */
            if(bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对比 使用缓冲流  和  不使用缓冲流   的效率
     */
    @Test
    public void BufferedStreamCopyTest(){
        Long startTime = System.currentTimeMillis();
        String src = "src/main/resource/io/imgTest/waterfall.jpg";
        String dest = "src/main/resource/io/imgTest/waterfall(字节缓冲流copy副本).jpg";
        myBufferedStreamCopy(src,dest);
        Long endTime = System.currentTimeMillis();
        System.out.println("字节缓冲流耗费的时间："+ (endTime-startTime));


        startTime = System.currentTimeMillis();
        dest = "src/main/resource/io/imgTest/waterfall(字节流copy副本).jpg";
        myNoTextCopy(src,dest);
        endTime = System.currentTimeMillis();
        System.out.println("字节流耗费的时间："+(endTime-startTime));
    }

    /**
     * 字节缓冲流copy。 完成非文本文件的复制。
     * @param src
     * @param dest
     */
    @Test
    public void myBufferedStreamCopy(String src,String dest){
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            /**
             * 1.创建字节流对象
             */
            FileInputStream fileInputStream = new FileInputStream(src);
            FileOutputStream fileOutputStream = new FileOutputStream(dest);
            /**
             * 2.创建缓冲流对象，来包装节点流
             */
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            /**
             * 3.读取指定文件的内容
             */
            byte[] bytes = new byte[1024];
            int len = 0;

            while( (len = bufferedInputStream.read(bytes))!= -1){
                /**
                 * 4.将指定内容写到目标地点
                 *  “读取的缓冲区”读多少个，就写多少个。 “读取的缓冲区”和下面 “缓冲流的缓冲区”（下面简称缓冲区），不是一回事
                 *  提高效率的原因：缓冲流有一个默认的缓冲区，大小为8192B  即8KB
                 *      当缓冲区被写满了/或者流正常关闭之后，就会往文件里面写
                 */
                // 这里可以理解为把“读取的缓冲区” 写入到了 缓冲流的缓冲区中。当写满8K/或者流正常关闭之后，缓冲流就会把数据写到指定文件里
                bufferedOutputStream.write(bytes,0,len);
            }
            /**
             * 强制清空缓冲区
             * 优点：可以避免当程序异常关闭时，缓冲区中的内容不会丢失
             * 缺点：每读一次，就清空一次缓冲区。和没有使用缓冲流差的效率不多
             */
//                bufferedOutputStream.flush();
            System.out.println("字节缓冲流copy.完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 5.关闭流. 只用关闭缓冲流就可以了，缓冲流会自动关闭节点流
             *  只需要关闭最后一个处理流就可以了。他会关闭前面所有的，包括节点流
             */
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试 字符缓冲流copy。 完成文本文件的复。（字符流可以一次读一行）。
     */
    @Test
    public void BufferedReaderCopyTest(){
        String src = "src/main/resource/textBranch";
        String dest = "src/main/resource/textBranch(字符缓冲流copy副本)";
        myBufferedReaderCopy(src,dest);
    }

    /**
     * 字符缓冲流copy。 完成文本文件的复。（字符流可以一次读一行）。
     */
    @Test
    public void myBufferedReaderCopy(String src,String dest){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            /**
             * 1.创建字符节点。输入输出的文件都在这里定义
             */
            FileReader fileReader = new FileReader(src);
            FileWriter fileWriter = new FileWriter(dest);

            /**
             * 2.创建字符缓冲流，来包装字符节点流
             */
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            /**
             * 3.读取文件内容。一次读一行
             */
            String str = null;
            while( (str = bufferedReader.readLine()) !=null){
                /**
                 * 4.写入到指定文件中去
                 */
                bufferedWriter.write(str);
                bufferedWriter.newLine();   // 换行
            }
            System.out.println("字符缓冲流copy.完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             *5.关闭流。 只需要关闭缓冲流即可。因为缓冲流会关闭节点流
             *  只需要关闭最后一个处理流就可以了。他会关闭前面所有的，包括节点流
             */
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //=====================缓冲流 end=========================
}
