package com.rl.pc;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;

import java.io.IOException;

public class HttpRequest {
    /**
     * json 字符串
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        /*  生成 HttpClinet 对象并设置参数 */
        HttpClient httpClient = new HttpClient();
        // 设置 Http 连接超时为秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000);
        /*  生成 GetMethod 对象并设置参数 */
        GetMethod getMethod = new GetMethod(url);
        // 设置 get 请求超时为  秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,6000 );
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        /*  执行 HTTP GET 请求 */
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            /*  判断访问的状态码 */
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错: " + getMethod.getStatusLine());
            }
            /*  处理 HTTP 响应内容 */
            // HTTP响应头部信息，这里简单打印
            Header[] headers = getMethod.getResponseHeaders();
          /*  for (Header h : headers)
                System.out.println(h.getName() + "------------ " + h.getValue());*/
            // 读取 HTTP 响应内容，这里简单打印网页内容
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            response = new String(responseBody);
          //  System.out.println("----------response:" + response);
            // 读取为 InputStream，在网页内容数据量大时候推荐使用
            // InputStream response = getMethod.getResponseBodyAsStream();
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            System.out.println("发生网络异常!");
            e.printStackTrace();
        } finally {
            /*  .释放连接 */
            getMethod.releaseConnection();
        }
        return response;
    }

    /**
     * post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json) {
       /* DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result, JSONObject.class);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        return null;
    }
}
