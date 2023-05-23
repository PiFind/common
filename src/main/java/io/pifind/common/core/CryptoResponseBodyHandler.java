package io.pifind.common.core;

import io.pifind.common.response.IResponseBodyHandler;
import io.pifind.common.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CryptoResponseBodyHandler implements IResponseBodyHandler {

    @Autowired
    private CryptoHandler cryptoHandler;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public <T> R<T> handle(R<T> response) {
        response.setCode(cryptoHandler.standardize(response.getCode()));
        return response;
    }

}
