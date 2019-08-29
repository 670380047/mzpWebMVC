package util.httpclient;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/8/29 11:12
 * @File : HttpClient
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/8/29 11:12
 */
public class HttpClient {
    public static void main(String args[]){
        try {
//            doJson();
            jsonRequest();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通客户端测试（服务器之间的通信可使用该方式） 方式一，更熟悉一点
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void jsonRequestOne() throws URISyntaxException, IOException {
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        //请求的地址
//        String url = "http://localhost:8080/mzpWebMVC/mzp/mv1";
        //String url = "http://localhost:8080/mzpWebMVC/mzp/handle1";
        try {
            String url = "http://localhost:8080/NmUserDataSyn/billing/plca/prodInstAttr";
            //1.创建HttpRequest(内部使用HttpURLConnection)
            ClientHttpRequest request = new SimpleClientHttpRequestFactory()
                    .createRequest(new URI(url), HttpMethod.POST);
            HttpHeaders headers = request.getHeaders();
            //2.设置客户端可接受的媒体类型（即需要什么类型的响应数据） accept和控制层的produce匹配
            request.getHeaders().set("Accept","application/json");
            //Content-Type和控制层的consumes匹配
            request.getHeaders().set("Content-Type","text/html");
            request.getHeaders().set("appid","1234");  //非必须。这个额外设置的一些自定义请求头
            request.getHeaders().set("appkey","123456");  //非必须。这个额外设置的一些自定义请求头
            String json = "{\"prodInstAttr\":[{\"attrId\":\"attrId6666\"}, {\"name\":\"mzp\",\"age\":\"13\"}]}";
            request.getBody().write(json.getBytes(Charset.forName("utf-8")));
            //3.发送请求并得到响应
            ClientHttpResponse response = request.execute();
            //4.得到响应体的编码方式
            Charset charset = response.getHeaders().getContentType().getCharset();
            //5.得到响应体的内容.将响应体的内容作为输入流 （InputStream是所有字节输入流动父类）
            inputStream = response.getBody();
            //6.将字节输入流InputStream通过InputStreamReader()转换为字符输入流BufferedReader。
            // 因为转换为字符输入流BufferedReader的读取效率更高，可以从数据源一行一行的读数据
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String jsonData = "";
            System.out.println("编码方式charset:"+charset+"  json data如下:");
            while( (jsonData = bufferedReader.readLine())!=null ){
                System.out.println(jsonData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }

        //response.getHeaders()  可以得到响应头，从而可以得到响应体的内容类型和编码、内容长度
//        byte[] bytes = new byte[(int) response.getHeaders().getContentLength()];
//        //从输入源中读出数据，放入bytes数组中
//        inputStream.read(bytes);
//        String jsonData = new String(bytes,charset);
//        System.out.println("编码方式charset:"+charset+"  json data:"+jsonData);
    }


    /**
     * 普通客户端测试（服务器之间的通信可使用该方式）方式二
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void jsonRequest() throws URISyntaxException, IOException {
//        String urlString = "http://localhost:8080/NmUserDataSyn/billing/plca/prodInstAttr";
        String urlString = "http://localhost:8181/getAll?start=2";
        String jsonBody ;
        BufferedReader bufferedReader = null;
        String result = "";
        //设置访问链接
        try {
            URL  url= new URL(urlString);
            //打开链接
            URLConnection conn = url.openConnection();
            //2.设置客户端可接受的媒体类型（即需要什么类型的响应数据） accept和控制层的produce匹配
            conn.setRequestProperty("accept","application/json");  //("accept","*/*") 表示所有类型
            conn.setRequestProperty("connection","Keep-Alive");
            //Content-Type和控制层的consumes匹配
            conn.setRequestProperty("Content-Type","text/html");
            conn.setRequestProperty("appid","1234");  //非必须。这个额外设置的一些自定义请求头
            conn.setRequestProperty("appkey","123456");  //非必须。这个额外设置的一些自定义请求头
            conn.setRequestProperty("prodInstAttr","attr66666");
            //发送post请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //BufferedReader输入输出流来读取url的响应
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }
        System.out.println("测试结果："+result);
    }


    public static void doJson(){

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"prodInstAttr\":\"attr1\" , \"name\":\"mzp\"}";
        try {
            Map<String,String> jsonToMap = objectMapper.readValue(json, HashMap.class);
            System.out.println("测试："+jsonToMap);
            System.out.println("测试结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
