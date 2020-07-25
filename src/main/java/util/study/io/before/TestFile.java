package util.study.io.before;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/19 15:45
 * @File : TestFile
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * java.io.File类:用于描述文件或目录。用于一些文件或目录的基本操作，如:新建、删除、重命名等
 *      但是，若需要操作文件的内容，File类将无能为力。
 *      因此，通常使用File类的对象与IO流配合使用，可以将File类的对象作为参数，传递给IO流的构造器
 *
 * 绝对路径/相对路径：
 *
 * 一、访问文件名:
 *      getName()
 *      getPath()
 *      getAbsoluteFile()
 *      getAbsolutePath()
 *      getParent()
 * 二、重命名
 *       renameTo(File newName)
 *          boolean flag =  file1.renameTo(file2)
 *           注意：file1必须存在，file2必须不存在，才能重命名成功
 *
 * 三、文件检测
 *      exists()    //是否存在
 *      canWrite()  //是否可写
 *      canRead()   //是否可读
 *      isFile()    //是不是一个文件
 *      isDirectory()   //是不是一个目录
 *
 * 四、获取常规文件信息
 *      lastModified()    // 获取最后一次的修改时间
 *      length()    //获取文件大小，单位是KB。 只有文件可以查看大小，目录不可以
 *
 * 五、文件操作相关
 *      createNewFile()  //创建新文件
 *      delete()        //文件删除。文件可以直接删除。  如果是删除目录的话，必须把目录下面的东西全部删除完毕之后，才能删除这个目录成功
 *
 * 六、目录操作相关
 *      mkDir()     //创建一个目录
 *      mkDirs()     // 创建一个目录，如果路径中的父级目录不存在，也会一并创建
 *      list()      // 返回指定路径下的所有文件的文件名称，是一个字符串数组
 *      listFiles()
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/19 15:45
 */
public class TestFile {
    /**
     * 一、访问文件名:
     * getName()    //获取文件名
     * getPath()    //获取相对路径
     * getAbsoluteFile()    //获取绝对文件
     * getAbsolutePath()    //获取绝对路径
     * getParent()  //获取父路径
     * renameTo(File newName)   //重命名
     */
    @Test
    public void test1(){
        File file1 = new File("src/main/resource/io/testFile.txt");
        System.out.println("文件名："+file1.getName());
        System.out.println("相对路径："+file1.getPath());
        System.out.println("绝对文件："+file1.getAbsoluteFile());
        System.out.println("绝对路径名："+file1.getAbsolutePath());
        System.out.println("父路径："+file1.getParent());

        System.out.println("--------------------------------------------");
        File file2 = new File("src/main/resource/io");
        System.out.println("文件名："+file2.getName());
        System.out.println("相对路径："+file2.getPath());
        System.out.println("绝对文件："+file2.getAbsoluteFile());
        System.out.println("绝对路径名："+file2.getAbsolutePath());
        System.out.println("父路径："+file2.getParent());
    }

    /**
     * 二、重命名
     *      renameTo(File newName)
     *            boolean flag =  file1.renameTo(file2)
     *            注意：file1必须存在，file2必须不存在，才能重命名成功
     */
    @Test
    public void test2(){
        File file1 = new File("src/main/resource/io/testFile.txt");
        File file2 = new File("src/main/resource/io/file/testFile123.txt");
        boolean flag =  file1.renameTo(file2);
        System.out.println("文件重命名："+flag);
    }

    /**
     * 三、文件检测
     *      exists()
     *      canWrite()
     *      canRead()
     *      isFile()
     *      isDirectory()
     */
    @Test
    public void test3(){
        File file1 = new File("src/main/resource/io/File/isExist.txt");
        System.out.println("是否存在："+file1.exists());
        System.out.println("是否可写："+file1.canWrite());
        System.out.println("是否可读："+file1.canRead());
        System.out.println("是否是一个文件："+file1.isFile());
        System.out.println("是否是一个目录:"+file1.isDirectory());

        System.out.println("-----------------------------------------------------");

        File file2 = new File("src/main/resource/io/File/checkFile");
        System.out.println("是否存在："+file2.exists());
        System.out.println("是否可写："+file2.canWrite());
        System.out.println("是否可读："+file2.canRead());
        System.out.println("是否是一个文件："+file2.isFile());
        System.out.println("是否是一个目录:"+file2.isDirectory());

        System.out.println("-----------------------------------------------------");

        File file3 = new File("src/main/resource/io/File/");
        System.out.println("是否存在："+file3.exists());
        System.out.println("是否可写："+file3.canWrite());
        System.out.println("是否可读："+file3.canRead());
        System.out.println("是否是一个文件："+file3.isFile());
        System.out.println("是否是一个目录:"+file3.isDirectory());
    }

    /**
     * 四、获取常规文件信息
     *      lastModified()    // 获取最后一次的修改时间
     *      1ength()    //获取文件大小，单位是KB。 只有文件可以查看大小，目录不可以
     */
    @Test
    public void test4(){
        File file1 = new File("src/main/resource/io/File/checkFile");
        long time = file1.lastModified();
        System.out.println(time);
        Date date = new Date(time);
        System.out.println("最后一次修改的时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        System.out.println("文件大小："+file1.length()+"字节(KB)"); //只有文件可以查看大小，目录不可以

        File file2 = new File("src/main/resource/io/File/");
        System.out.println("目录大小(目录不可以查看大小)："+file2.length());
    }


    /**
     * 五、文件操作相关
     *      createNewFile()  //创建新文件
     *      delete()        //文件删除。文件可以直接删除。  如果是删除目录的话，必须把目录下面的东西全部删除完毕之后，才能删除这个目录成功
     */
    @Test
    public void test5(){
        File file1 = new File("src/main/resource/io/File/createFile测试");
        boolean createFlag = false;
        boolean deleteFlag = false;
        try {
            if(!file1.exists()){
                createFlag = file1.createNewFile();
            }else{
                System.out.println(file1.getName()+"已存在：");
            }
            System.out.println(file1.getName()+"是否创建成功："+createFlag);

            System.out.println("线程暂停10秒，查看文件是否创建。3秒后即将删除");
            Thread.sleep(10000);
            deleteFlag = file1.delete();
            System.out.println(file1.getName()+"是否删除成功："+deleteFlag);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 六、目录操作相关
     *      mkDir()
     *      mkDirs()
     *      list()
     *      listFiles()
     */
    @Test
    public void test6(){
        File file1 = new File("src/main/resource/io/File/mkdir");

        boolean createFlag = false;
        if(!file1.exists()){
            createFlag = file1.mkdir();
        }else{
            System.out.println(file1.getName()+"已存在：");
        }
        System.out.println("目录创建成功："+createFlag);

        System.out.println("-----------------------------------");
        File file2 = new File("src/main/resource/io");
        String[] strs = file2.list();
        Arrays.stream(strs).forEach(System.out::println);

        System.out.println("-----------------------------------");

         File[] files = file2.listFiles();
         Arrays.stream(files).forEach((e)->{
             System.out.println(e.getName()+"===>"+e.getPath());
         });

    }

}
