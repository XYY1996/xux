package com.xux.imc.template.api.rest;

import lombok.Getter;
import lombok.Setter;

public enum ResultCode {

    FAIL(500,"500","失败","500","失败"),
    SUCCESS(200,"200","成功",null,null);

    @Getter
    private Integer http;
    @Getter
    private String code;
    @Getter
    private String msg;
    @Getter
    private String errorCode;
    @Getter
    @Setter
    private String errorMsg;

    ResultCode(Integer http, String code, String msg, String errorCode, String errorMsg) {
        this.http = http;
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
