package com.cainiao.wechat.config;

import com.cainiao.wechat.service.WechatApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author Liqm
 * @Date 2020/10/25 0025
 */
@Slf4j
@Configuration
@Import(WechatApi.class)
@EnableConfigurationProperties(WechatProperties.class)
public class WechatConfigruation {

    public WechatConfigruation() {
        log.info("init WechatConfigruation");
    }

    @Bean
    public WechatApi wechatApi(WechatProperties wechatProperties) {
        return new WechatApi(wechatProperties);
    }

}
