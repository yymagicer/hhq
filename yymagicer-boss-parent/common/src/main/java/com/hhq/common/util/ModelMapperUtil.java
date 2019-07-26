package com.hhq.common.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * <Description> 对象转换工具<br>
 *
 */
public class ModelMapperUtil {

    /**
     * 定义基础modelMapper对象
     */
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        // 精准匹配a
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * 默认构造函数
     */
    private ModelMapperUtil() {

    }

    /**
     * Description: dto转换-通用精确匹配 (精确匹配是指只copy相同属性名的值) 自定义匹配规则请参考：http://modelmapper.org/getting-started/ 的‘Explicit Mapping’部分<br>
     * 
     * @author yymagicer<br>
     * @taskId <br>
     * @param source 源对象
     * @param destinationType 目标对象类型
     * @param <D> 泛型对象
     * @return <br>
     */
    public static <D> D strictMap(Object source, Class<D> destinationType) {
        if (null==source) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    /**
     * Description: List&lt;dto>转换<br>
     * 
     * @author yymagicer<br>
     * @taskId <br>
     * @param source 源对象
     * @param componentType 目标对象类型
     * @param <D> 泛型对象
     * @return <br>
     */
    @SuppressWarnings("unchecked")
    public static <D> List<D> strictMapList(Object source, final Class<D> componentType) {
        if (null==source) {
            return new ArrayList();
        }
        List<D> list = new ArrayList();
        List<Object> objectList = (List<Object>) source;
        for (Object obj : objectList) {
            list.add(modelMapper.map(obj, componentType));
        }
        return list;
    }
}
