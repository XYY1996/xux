package ${packageStr};

import java.io.Serializable;
import java.util.List;

${importStr}

/**
 * 
 * ${entityDesc}Dao
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 *
 * </pre>
 * @since 1.
 */
public interface ${className} {
    /**
     * 批量新增
     * 
     * @param list
     * @return
     */
    int addBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 批量更新
     * 
     * @param list
     * @return
     */
    int updateBatch(@Param("list")List<${entityClassName}> list);
    
    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    int deleteBatch(@Param("list")List<Long> list);
    
    /**
     * 查询列表
     * 
     * @param entity
     * @return
     */
    List<${entityClassName}> queryList(${entityClassName} entity);
}