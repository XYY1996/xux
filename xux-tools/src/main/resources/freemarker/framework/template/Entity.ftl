package ${packageStr};
import java.io.Serializable;
import lombok.Data;

${importStr}

/**
 * 
 * ${entityDesc}实体
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  ${time} Created
 *
 * </pre>
 * @since 1.
 */
@Data
public class ${className} implements Serializable {
    private static final long serialVersionUID = ${serialVersionNum};
    
${propertiesStr}
}