package com.xux.dao.base.mapper;

import com.xux.dao.base.entity.SysParams;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * ows_system_param Mapper 接口
 * </p>
 *
 * @author xuyy5
 * @since 2020-01-13
 */
public interface SysParamsMapper extends BaseMapper<SysParams> {

    SysParams selectByPrimaryKey(String name);

}
