package com.cainiao.wechat.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Liqm
 * @Date 2020/10/24 0024
 */
@Data
@ToString
@ConfigurationProperties(prefix = "wechat.auth")
public class WechatProperties {

    private String appId;

    private String appsecret;
}
