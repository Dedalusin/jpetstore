package org.csu.jpetstore.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;
public class HttpUtils {
    public static JSONObject post(String url) {
        HttpPost post = new HttpPost(url);
        return getResult(post);
    }
    /*
     *发送带Header的post请求
     */
    public static JSONObject post(String url, Map<String, String> map) {
        HttpPost post = new HttpPost(url);
        if (!map.isEmpty()) {
            Set<Map.Entry<String, String>> entrys = map.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                post.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return getResult(post);
    }
    /*
     *发送带Header的get请求
     */
    public static JSONObject get(String url, Map<String, String> map) {
        HttpGet get = new HttpGet(url);
        if (!map.isEmpty()) {
            Set<Map.Entry<String, String>> entrys = map.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return getResult(get);

    }
    /*
     *发送简单的get请求
     */
    public static JSONObject get(String url) {
        HttpGet get = new HttpGet(url);
        return getResult(get);

    }
    /*
     *发送请求方法，请求响应为JSONObject
     */
    private static JSONObject getResult(HttpRequestBase requestBase) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(requestBase).getEntity());
            result = new String(result.getBytes("ISO-8859-1"),"utf-8");
            httpClient.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            return new JSONObject(JSON.parseObject(result));
        }
    }
    /*
     *当请求响应为String时
     */
    public static String getString(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(get).getEntity());
            httpClient.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            return result;
        }
    }

}
