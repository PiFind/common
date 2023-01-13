package io.pifind.common.response;

import java.util.Comparator;

/**
 * 响应拦截器，对当前响应进行拦截
 */
public interface IResponseBodyHandler {

    /**
     * 拦截优先级顺序
     * <p>值越小优先级越高</p>
     * @return 优先级值
     */
    int order();

    /**
     * 蓝机器处理方法
     * @param response 源结果
     * @return 处理后的结果
     * @param <T> 原结果中数据类型
     */
    <T> R<T> handle(R<T> response);

    /**
     * 优先级顺序比较器
     */
    Comparator<IResponseBodyHandler> ORDER_COMPARATOR =
            (o1, o2) -> o1.order() - o2.order();

}