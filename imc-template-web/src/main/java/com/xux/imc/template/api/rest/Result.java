package com.xux.imc.template.api.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "返回结果")
public class Result<T> extends BaseResult {
    @ApiModelProperty("业务数据")
    private T data;

    Result() {
    }
    Result(ResultCode resultCode, T data) {
        super(resultCode);
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS, data);
    }

    public static Result fail() {
        return new Result(ResultCode.FAIL, null);
    }

    public static Result fail(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    public static Result fail(ResultCode resultCode, String errormsg) {
        resultCode.setErrorMsg(errormsg);
        return new Result(resultCode, null);
    }
}
