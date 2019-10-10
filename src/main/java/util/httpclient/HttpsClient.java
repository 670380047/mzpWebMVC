package util.httpclient;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/10 19:54
 * @File : HttpsClient
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/10 19:54
 */
public class HttpsClient {

    private static RequestConfig requestConfig = null;
    public static final String HTTP_TYPE = "https";
    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
    }

    /***
     * description:
     * @author maozp3
     * @date: 10:12 2019/9/21
     * @param: [jsonParam]
     * @return JSONObject
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, String type){
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient =  null;
//        CloseableHttpClient client = HttpClientBuilder.create().build();
        //如果url为空，就给个默认值
        //创建httpClient
        if(HTTP_TYPE.equalsIgnoreCase(type)){
            httpClient = HttpsClient.sslClient();
        }else {
            httpClient = HttpClients.createDefault();
        }
        HttpPost httpPost = new HttpPost(url);
        //设置请求和传输的超时时间
        httpPost.setConfig(requestConfig);
        try {
            if(jsonParam != null){
                //解决中文乱码问题
                StringEntity entity = new StringEntity(String.valueOf(jsonParam),"utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
                httpPost.setHeader("appId","1234");
                httpPost.setHeader("appKey","123456");
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            if(result.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String str="";
                // 读取服务器返回过来的json字符串数据
                str = EntityUtils.toString(result.getEntity(),"utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        String param = "{\"orderType\":\"test1234\",\"appKey\":\"123456\"}";
        String url = "http://localhost:8080/NmUserDataSyn/billing/plca/tingfuji";
        System.out.println(param);
        jsonObject = JSONObject.parseObject(param);

        jsonObject = httpPost(url,jsonObject,"https");
        System.out.println("jsonObject输出结果："+jsonObject);
    }


    /**
     * @Author zhengxq
     * @Description 在调用SSL之前需要重写验证方法，取消检测SSL
     *              创建ConnectionManager，添加Connection配置信息
     * @Date 2018/12/27 0027 16:59
     * @Param []
     * @return org.apache.http.client.HttpClient 支持https
     **/
    private static CloseableHttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
            };

            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }



}
