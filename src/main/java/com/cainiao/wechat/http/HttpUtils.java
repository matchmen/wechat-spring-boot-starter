package com.cainiao.wechat.http;

import com.cainiao.wechat.exception.WechatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author Liqm
 * @Date 2020/10/26 0026
 */
@Slf4j
public class HttpUtils {

    private static String ENCODING_TYPE_UTF8 = "UTF-8";

    public static String httpGet(String url) {
        log.info("request get url:{}", url);
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("httpGet request -》url can be not null or blank.");
        }
        HttpGet httpGet = new HttpGet(url);
        String result = execute(httpGet);
        log.info("request result:{}", result);
        return result;
    }

    private static String execute(HttpRequestBase httpRequest) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);) {
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = httpResponse.getEntity();
                return EntityUtils.toString(entity, ENCODING_TYPE_UTF8);
            }else {
                log.error("http request status exception:{}", httpResponse);
                throw new WechatException("微信接口异常，请稍后重试");
            }
        } catch (IOException e) {
            log.error("http request exception:{}", e);
            throw new WechatException("微信接口异常，请稍后重试.");
        }
    }

    public static void main(String[] args) {
        httpGet("https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code");
    }
}
