package com.cainiao.wechat.exception;

import lombok.Getter;

/**
 * @Author Liqm
 * @Date 2020/10/26 0026
 */
public class WechatException extends RuntimeException{

    @Getter
    private String message;

    public WechatException(String message) {
        this.message = message;
    }

}
