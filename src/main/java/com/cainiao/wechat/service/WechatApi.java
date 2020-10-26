package com.cainiao.wechat.service;

import com.alibaba.fastjson.JSON;
import com.cainiao.wechat.bean.LoginResponse;
import com.cainiao.wechat.config.WechatProperties;
import com.cainiao.wechat.exception.WechatException;
import com.cainiao.wechat.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author Liqm
 * @Date 2020/10/25 0025
 */
@Slf4j
public class WechatApi {

    //登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
    private static String GET_WECHAT_INFO_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    private WechatProperties wechatProperties;

    public WechatApi(WechatProperties wechatProperties) {
        log.info("init Wechat Api,wechat config:{}", wechatProperties);
        this.wechatProperties = wechatProperties;
    }

    public LoginResponse login(String code) {
        String loginUrl = String.format(GET_WECHAT_INFO_URL, wechatProperties.getAppId(), wechatProperties.getAppsecret(), code);
        String result = HttpUtils.httpGet(loginUrl);
        LoginResponse loginResponse = JSON.parseObject(result, LoginResponse.class);

        if(Objects.isNull(loginResponse)){
            log.error("wechat login api return:{}", loginResponse);
            throw new WechatException("微信接口异常，请稍后重试");
        }
        if (0 == loginResponse.getErrcode()) {
            return loginResponse;
        }
        log.error("wechat login api return:{}", loginResponse);
        throw new WechatException(loginResponse.getErrmsg());
    }

    public static void main(String[] args) {
        System.out.println(String.format(GET_WECHAT_INFO_URL, "111", "222", "code"));
    }

}
