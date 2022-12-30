package io.pifind.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 查询对象注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryObject {

    /**
     * 设置查询对象对应的API
     * @return API地址数组
     */
    String[] value();

    /**
     * 默认使用第几个API
     * @return 记录API的索引
     */
    int defaultAPI() default 0;

}
