package io.pifind.common.config;

import io.pifind.common.i18n.MessageBundle;
import io.pifind.common.i18n.ResponseMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseBodyHandlerManager {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public io.pifind.common.response.ResponseBodyHandlerManager responseBodyHandlerManager() {
        io.pifind.common.response.ResponseBodyHandlerManager responseBodyHandlerManager = new io.pifind.common.response.ResponseBodyHandlerManager();
        responseBodyHandlerManager.add(
                new ResponseMessageHandler(
                        new MessageBundle(messageSource)
                )
        );
        return responseBodyHandlerManager;
    }

}
