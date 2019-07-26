package ${basePackage}.po;

import com.hhq.common.base.BasePO;
import lombok.Data;
/**
 * 
 */
@Data
@Table(name="${tableName}")
public class ${beanName?cap_first}PO extends BasePO{
	<#list columnList as column>
	/**
	 * ${column.columnComment}
	 */
	private ${column.propertyType} ${column.propertyName?uncap_first};
	</#list>
	
}

