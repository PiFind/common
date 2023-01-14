package io.pifind.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标注错误码枚举的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorCode {

    /**
     * 判断是否需要翻译（国际化）
     * @return 返回值为 boolean 类型
     * <ul>
     *     <li><b>需要翻译</b> - 返回 {@code true}</li>
     *     <li><b>不需要翻译</b> - 返回 {@code false}</li>
     * </ul>
     */
    boolean translate() default true;

}
