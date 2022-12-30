package io.pifind.common.util;

import io.pifind.common.annotation.QueryObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

/**
 * URI工件通用工具包
 * <p>
 *     主要用与于对URI拼接的处理，你可以将需要请求的参数设计为一个实体类，
 *     然后使用 {@link QueryObject} 注解对其进行标注，你就可以方便的
 *     使用拼装工具直接得到一个拼装好的 {@link java.net.URI} 对象了。
 * </p>
 * <p  color = "red">
 *     <b>注意：</b> 该拼装工具仅支持拼装一维数组，不支持
 *     拼装二维及多维数组。
 * </p>
 * @see QueryObject
 */
public class UriSplicedUtils {

    /**
     * 拼接URI参数
     * @param uri 需要拼接的uri字符串
     * @param obj 需要拼接的参数对象
     * @return 拼接好的uri
     */
    @SuppressWarnings("rawtypes")
    public static URI splice(String uri,Object obj) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        try {
            Map<String,Object> map =  PropertyUtils.describe(obj);
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value == null) {
                    continue;
                }
                // 拼接对象
                if (value instanceof Collection) {
                    Collection items = (Collection)value;
                    for (Object item : items) {
                        params.add(key,item.toString());
                    }
                } else if(value.getClass().isArray()) {
                    // 对数组进行遍历
                    int length = Array.getLength(value);
                    for (int i = 0; i < length ; i ++) {
                        Object item = Array.get(value,i);
                        params.add(key,String.valueOf(item));
                    }
                } else {
                    params.add(key,value.toString());
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return splice(uri,params);
    }

    /**
     * 拼接URI参数并转换为字符串
     * @param uri 需要拼接的uri字符串
     * @param obj 需要拼接的参数对象
     * @return 拼接好的uri字符串
     */
    public static String spliceToString(String uri, Object obj) {
        return splice(uri,obj).toString();
    }

    /**
     * 检查并拼接URI参数
     * <p color = "red">
     *     <b>注意：</b>被拼接的对象需要被 {@link QueryObject} 注解标注
     * </p>
     * @param uri 需要拼接的uri字符串
     * @param obj 需要检查并拼接的参数对象
     * @return 拼接好的uri字符串
     * @throws RuntimeException 如果对象没有通过检查或没有被 {@link QueryObject} 注解标注，就会抛出此异常
     * @see QueryObject
     */
    public static URI checkAndSplice(String uri, Object obj) {
        if (checkQueryUri(uri,obj.getClass())) {
            return splice(uri,obj);
        } else {
            throw new RuntimeException("不可访问");
        }
    }

    /**
     * 检查并拼接URI参数并转换为字符串
     * <p color = "red">
     *     <b>注意：</b>被拼接的对象需要被 {@link QueryObject} 注解标注
     * </p>
     * @param uri 需要拼接的uri字符串
     * @param obj 需要检查并拼接的参数对象
     * @return 拼接好的uri字符串
     * @throws RuntimeException 如果对象没有通过检查或没有被 {@link QueryObject} 注解标注，就会抛出此异常
     * @see QueryObject
     */
    public static String checkAndSpliceToString(String uri, Object obj) {
        return checkAndSplice(uri,obj).toString();
    }

    /**
     * 对被 {@link QueryObject} 注解标注的对象进行拼接
     * @param obj 需要拼接的uri字符串
     * @return 拼接好的uri
     * @throws RuntimeException 如果对象没有被 {@link QueryObject} 注解标注，就会抛出此异常
     * @see QueryObject
     */
    public static URI splice(Object obj) {
        Class<?> clazz = obj.getClass();
        if (clazz.isAnnotationPresent(QueryObject.class)) {
            QueryObject queryObjectAnno = clazz.getAnnotation(QueryObject.class);
            String uri = queryObjectAnno.value()[queryObjectAnno.defaultAPI()];
            return splice(uri,obj);
        } else {
            throw new RuntimeException("未标注注解");
        }
    }

    /**
     * 对被 {@link QueryObject} 注解标注的对象进行拼接，将拼接结果转换为字符串
     * @param obj 需要拼接的uri字符串
     * @return 拼接好的uri字符串
     * @throws RuntimeException 如果对象没有被 {@link QueryObject} 注解标注，就会抛出此异常
     * @see QueryObject
     */
    public static String spliceToString(Object obj) {
        return UriSplicedUtils.splice(obj).toString();
    }

    /**
     * 拼接URI参数
     * @param uri 需要拼接的uri字符串
     * @param params 参数
     * @return 拼接好的uri
     */
    public static URI splice(String uri, MultiValueMap<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri)
                .queryParams(params);
        return builder.build().toUri();
    }

    /**
     * 拼接URI参数转换为字符串
     * @param uri 需要拼接的uri字符串
     * @param params 参数
     * @return 拼接好的uri字符串
     */
    public static String spliceToString(String uri, MultiValueMap<String, String> params) {
        return splice(uri,params).toString();
    }

    /**
     * 检查对象类型是否能够进行请求
     * @param uri 需要拼接的uri字符串
     * @param clazz 需要检查的类型
     * @return {@code true} 可以查询 / {@code false} 不可以查询
     * @param <T> 检查类的泛型
     */
    private static <T> boolean checkQueryUri(String uri,Class<T> clazz) {
        if (clazz.isAnnotationPresent(QueryObject.class)) {
            QueryObject queryObjectAnno = clazz.getAnnotation(QueryObject.class);
            for (String enabledUri : queryObjectAnno.value()) {
                if (uri.equals(enabledUri)) {
                    return true;
                }
            }
        }
        return false;
    }

}
