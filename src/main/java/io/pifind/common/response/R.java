package io.pifind.common.response;

import lombok.Data;

/**
 * 通用返回
 * @param <T> 返回的数据类型
 */
@Data
public class R<T> {

    /** 错误码 */
    private Integer code;

    /** 返回消息 */
    private String message;

    /** 返回的数据 */
    private T data;

    public R(Integer code, String message) {
        this(code,message,null);
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 直接返回成功结果
     * @param <T> data的类型
     * @return 成功结果
     */
    public static <T> R<T> success() {
        return success(null,null);
    }

    /**
     * 直接返回成功结果
     * @param message 消息
     * @param <T> data的类型
     * @return 成功结果
     */
    public static <T> R<T> success(String message) {
        return new R<>(StandardCode.SUCCESS,message,null);
    }

    /**
     * 直接返回成功结果
     * @param message 消息
     * @param data 数据
     * @param <T> data的类型
     * @return 成功结果
     */
    public static <T> R<T> success(String message,T data) {
        return new R<>(StandardCode.SUCCESS,message,data);
    }

    /**
     * 直接返回失败结果
     * @param <T> data的类型
     * @return 失败结果
     */
    public static <T> R<T> failure() {
        return failure(null);
    }

    /**
     * 直接返回失败结果
     * @param <T> data的类型
     * @param message 失败信息
     * @return 失败结果
     */
    public static <T> R<T> failure(String message) {
        return new R<>(StandardCode.FAILURE,message);
    }

}
