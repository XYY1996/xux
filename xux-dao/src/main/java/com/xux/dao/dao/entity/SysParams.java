package com.xux.dao.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ows_system_param
 * </p>
 *
 * @author xuyy5
 * @since 2020-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * NAME
     */
    @TableId("NAME")
    private String name;

    /**
     * LANG
     */
    @TableField("LANG")
    private String lang;

    /**
     * VALUE
     */
    @TableField("VALUE")
    private String value;

    /**
     * REMARK
     */
    @TableField("REMARK")
    private String remark;


}
