package util.httpclient;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/8/29 11:12
 * @File : HttpClient
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    /*
    日志 slf4j
     */
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static RequestConfig requestConfig = null;
    public static final String HTTP_TYPE = "https";
    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
    }

    public static void main(String args[]){

//            doJson();
//            jsonRequest();
       String param = "{\"orderType\":\"test1234\",\"appKey\":\"123456\"}";
       //请求地址开头需要加上   http://
       String url = "http://localhost:8080/NmUserDataSyn/billing/plca/tingfuji";
       JSONObject jsonParam = JSONObject.parseObject(param);
       System.out.println("请求地址："+url+"\n请求参数："+jsonParam.toString());
       JSONObject jsonResult = httpPost(url,jsonParam);
       System.out.println("json返回结果"+jsonResult);
    }


    /***
    * description: httpClient。 post请求访问（强烈推荐使用）
    * @author maozp3
    * @date: 16:50 2019/9/21
    * @param: [url, jsonParam]
    * @return com.alibaba.fastjson.JSONObject
    */
    public static JSONObject httpPost(String url, JSONObject jsonParam){
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient =  null;
        //两种创建 CloseableHttpClient 的方式 （一）
//        httpClient = HttpClientBuilder.create().build();

        //两种创建 CloseableHttpClient 的方式 （二）
        httpClient = HttpClients.createDefault();
        //创建post请求
        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输的超时时间
        httpPost.setConfig(requestConfig);
        try {
            if(jsonParam != null){
                //解决中文乱码问题
                StringEntity entity = new StringEntity(String.valueOf(jsonParam),"utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                //设置请求体body
                httpPost.setEntity(entity);
                //设置请求头Headers
                httpPost.setHeader("appId","1234");
                httpPost.setHeader("appKey","123456");
            }
            //由客户端发起post请求，并返回结果
            CloseableHttpResponse result = httpClient.execute(httpPost);
            if(result.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String str="";
                // 读取服务器返回过来的json字符串数据
                str = EntityUtils.toString(result.getEntity(),"utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(str);
            }
        } catch (Exception e) {
            logger.error("post请求提交失败。url="+url);
            e.printStackTrace();
        }
        return jsonResult;
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
