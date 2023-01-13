package io.pifind.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.pifind.common.annotation.ErrorCode;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通用返回
 * @param <T> 返回的数据类型
 */
@Data
public class R<T> implements Cloneable {

    /** 错误码 */
    private Integer code;

    /** 返回消息 */
    private String message;

    /** 返回的数据 */
    private T data;

    /**
     * 是否需要国际化翻译消息
     * <p>
     *     <font style="color:red;">
     *     <b>注意：</b> 该值不会通过json序列化返回
     *     </font>
     * </p>
     */
    @JsonIgnore
    private Boolean needTranslateMessage;

    /**
     * 国际化翻译消息需要的参数
     * <p>
     *     <font style="color:red;">
     *     <b>注意：</b> 该值不会通过json序列化返回
     *     </font>
     * </p>
     */
    @JsonIgnore
    private Object[] messageParams;

    public R(@NotNull Integer code, String message) {
        this(code,message,null);
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 克隆一个返回结果
     * <p>
     *     <font style="color:red;">
     *     <b>注意：</b> 这只是一个浅复制，请在确保数据安全的情况下使用
     *     </font>
     * </p>
     * @return 当前结果的克隆
     */
    @Override
    public R<T> clone() {
        R<T> newR = new R<>(code,message,data);
        newR.setNeedTranslateMessage(needTranslateMessage);
        newR.setMessageParams(messageParams);
        return newR;
    }

    /**
     * 直接返回成功结果
     * @return 成功结果
     * @param <T> data的类型
     */
    public static <T> R<T> success() {
        return success(StandardCode.SUCCESS_MESSAGE,null);
    }

    /**
     * 直接返回成功结果
     * @param message 消息
     * @return 成功结果
     * @param <T> data的类型
     */
    public static <T> R<T> success(String message) {
        return new R<>(StandardCode.SUCCESS,message,null);
    }

    /**
     * 直接返回成功结果
     * @param message 消息
     * @param data 数据
     * @return 成功结果
     * @param <T> data的类型
     */
    public static <T> R<T> success(String message,T data) {
        return new R<>(StandardCode.SUCCESS,message,data);
    }

    /**
     * 直接返回成功结果
     * @param data 数据
     * @return 成功结果
     * @param <T> data的类型
     */
    public static <T> R<T> success(T data) {
        return R.success(StandardCode.SUCCESS_MESSAGE,data);
    }

    /**
     * 直接返回失败结果
     * @return 失败结果
     * @param <T> data的类型
     */
    public static <T> R<T> failure() {
        return failure(StandardCode.FAILURE_MESSAGE);
    }

    /**
     * 直接返回失败结果
     * @param message 失败信息
     * @return 失败结果
     * @param <T> data的类型
     */
    public static <T> R<T> failure(String message) {
        return new R<>(StandardCode.FAILURE,message);
    }


    /**
     * 根据错误码枚举类返回失败结果
     * <p>
     *     错误码枚举需要被 {@link ErrorCode} 注解进行标注
     * </p>
     * @param errorCode 错误码枚举类的一个值
     * @return 失败结果
     * @param <E> 枚举类类型
     * @param <T> 数据类型
     * @see ErrorCode
     */
    public static <E extends Enum<E>,T> R<T> failure(E errorCode,Object...params) {
        Class<?> errorCodeClass = errorCode.getClass();
        if(errorCodeClass.isAnnotationPresent(ErrorCode.class)) {

            int code ;
            String messageId;

            try {

                // 获取错误码标准值
                Method codeMethod = errorCodeClass.getMethod("code");
                code = (int) codeMethod.invoke(errorCode);

                // 获取国际化的 messageId 方法
                Method messageIdMethod =  errorCodeClass.getMethod("messageId");
                messageId = (String) messageIdMethod.invoke(errorCode);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException  e) {
                throw new RuntimeException(e);
            }

            // 创建结果
            R<T> result = new R<>(code,messageId);

            // 设置国际化属性需要的字段
            result.setNeedTranslateMessage(true);
            result.setMessageParams(params);

            // 返回结果
            return result;

        } else {
            throw new RuntimeException("不能使用未注解的 ErrorCode");
        }
    }

}
