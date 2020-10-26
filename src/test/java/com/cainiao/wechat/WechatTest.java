package com.cainiao.wechat;

import com.cainiao.wechat.service.WechatApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Liqm
 * @Date 2020/10/26 0026
 */
@SpringBootTest
public class WechatTest {

    @Autowired
    private WechatApi wechatApi;

    @Test
    public void test() {
        wechatApi.login("as");
    }

}
