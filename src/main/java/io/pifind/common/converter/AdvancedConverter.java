package io.pifind.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * 高级转换器
 * @param <S> 源类型
 * @param <T> 目标类型
 */
public interface AdvancedConverter<S,T> extends Converter<S,T> {

    /**
     * 批量转换
     * @param list 源列表
     * @return 目标列表
     */
    default List<T> convert(List<S> list) {
        List<T> result = new ArrayList<>();
        for (S s : list) {
            result.add(convert(s));
        }
        return result;
    }

}
