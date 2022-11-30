package com.xux.imc.template.api.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseResult {

    @ApiModelProperty("http状态码")
    private int httpStatus;

    @ApiModelProperty("调用追踪id")
    private String traceId;
    @ApiModelProperty("结果编码")
    private String code;
    @ApiModelProperty("结果描述")
    private String msg;
    @ApiModelProperty("错误编码")
    private String errorCode;
    @ApiModelProperty("错误描述")
    private String errormsg;

    public BaseResult() {
    }

    public BaseResult(int httpStatus, String code, String msg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int httpStatus, String code, String msg, String errorCode, String errormsg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
        this.errorCode = errorCode;
        this.errormsg = errormsg;
    }

    public BaseResult(ResultCode resultCode) {
        this.httpStatus = resultCode.getHttp();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.errorCode = resultCode.getErrorCode();
        this.errormsg = resultCode.getErrorMsg();
    }
}
