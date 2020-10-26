package com.cainiao.wechat.bean;

import lombok.Data;

/**
 * @Author Liqm
 * @Date 2020/10/25 0025
 */
@Data
public class LoginResponse {

    private String openid;

    private String session_key;

    private String unionid;

    private int errcode;

    private String errmsg;
}
