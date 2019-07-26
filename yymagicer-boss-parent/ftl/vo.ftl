package ${basePackage}.gateway.${beanName?lower_case}.vo;

import com.hhq.common.base.BaseVO;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 */
@Data
public class ${beanName?cap_first}VO extends BaseVO implements Serializable {

	<#list columnList as column>
	/**
	 * ${column.columnComment}
	 */
	<#if column.nullable?? && column.nullable=='1'>
	@NotEmpty(groups ={Insert.class},message = "${column.columnComment}不能为空")
	</#if>
	private ${column.propertyType} ${column.propertyName?uncap_first};
	</#list>
}
