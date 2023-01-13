package io.pifind.common.config;

import io.pifind.common.i18n.MessageBundle;
import io.pifind.common.i18n.ResponseMessageHandler;
import io.pifind.common.response.ResponseBodyHandlerManager;
import io.pifind.common.response.StandardResponseBodyAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.pifind.common.response")
public class PiFindCommonConfiguration {

    @Autowired
    private MessageSource messageSource;

    /**
     * 响应内容处理器管理器
     * <p>
     *     里面自动注入了国际化的包
     * </p>
     * @return {@link ResponseBodyHandlerManager 响应内容处理器管理器实体对象}
     */
    @Bean
    public ResponseBodyHandlerManager responseBodyHandlerManager() {
        ResponseBodyHandlerManager responseBodyHandlerManager = new io.pifind.common.response.ResponseBodyHandlerManager();
        responseBodyHandlerManager.add(
                new ResponseMessageHandler(
                        new MessageBundle(messageSource)
                )
        );
        return responseBodyHandlerManager;
    }

}
