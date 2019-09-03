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
import java.nio.charset.Charset;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/27 9:11
 * @File : TestJson
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class TestJson {
    public static void main(String args[]){
        try {
            jsonRequest();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通客户端测试（服务器之间的通信可使用该方式）
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void jsonRequest() throws URISyntaxException, IOException {
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
            //样例：把“猫”这个字符串通过iso-8859-1解析之后，在通过utf-8来编码成新的字符串。
            //用法：可以把某字符串通过某种编码方式进行解析之后，进行传递。  传递到目的地之后，再用另一种编码解析回来。
//            String mzp = new String("猫".getBytes("iso-8859-1"),"utf-8");
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

    public void doJson(){

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"prodInstAttr\":\"attr1\" , \"name\":\"mzp\"}";

    }
}
