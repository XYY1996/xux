package com.xux.imc.template.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xux.imc.template.api.vo.valid.ValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "例子VO对象")
public class SimpleVO {

    @ApiModelProperty("name")
    @Null(groups = ValidGroup.Crud.Create.class)
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "name不能为空")
    private String name;

    @ApiModelProperty("value")
    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "value不能为空")
    private String value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone ="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("busiType")
    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "busiType不能为空")
    private String busiType;
}
