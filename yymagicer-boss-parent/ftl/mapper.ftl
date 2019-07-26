package ${basePackage}.mapper;

import ${basePackage}.po.${beanName?cap_first}PO;
import com.hhq.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ${beanName?cap_first}Mapper extends BaseMapper<${beanName?cap_first}PO> {
}