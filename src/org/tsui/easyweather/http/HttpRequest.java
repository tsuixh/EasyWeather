package org.tsui.easyweather.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TsuiXh on 16/11/21.
 */

/**
 * 用于发起http请求的网络连接类
 */
public class HttpRequest {
    /**
     * 读取网络中的xml天气数据
     *
     * @param uri xml文档地址
     * @return 以输入流的形式返回xml数据
     */
    public static InputStream read(String uri) throws IOException {

        InputStream is = null;
        URL url = new URL(uri);
        //打开链接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        connection.setRequestMethod("GET");
        //设置Timeout
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(5000);
        //获取响应码
        int stateCode = connection.getResponseCode();
        System.out.println("Response Code:" + stateCode);
        if (stateCode == HttpURLConnection.HTTP_OK) {
            //响应成功
            is = connection.getInputStream();
        }
        return is;
    }
}
