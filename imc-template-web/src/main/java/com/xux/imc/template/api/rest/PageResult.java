package com.xux.imc.template.api.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel(description = "返回结果")
public class PageResult<T> extends BaseResult {

    @ApiModelProperty("数据总条数")
    private Long total;
    @ApiModelProperty("业务数据")
    private List<T> data;

    PageResult() {
        super(ResultCode.SUCCESS);
    }

    public static <T> PageResult<T> success(Long total, List<T> data) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setData(data);
        return pageResult;
    }
}
