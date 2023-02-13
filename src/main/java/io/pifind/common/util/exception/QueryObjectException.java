package io.pifind.common.util.exception;

import io.pifind.common.exception.ServerExceptionCode;
import io.pifind.common.exception.ServerRuntimeException;

/**
 * 查询对象异常
 */
public class QueryObjectException extends ServerRuntimeException {

    public static final int CODE = ServerExceptionCode.LOCAL_TOOL_CLASS_EXCEPTION | 0x00A1;

    public QueryObjectException(String message) {
        super(CODE, message);
    }

}
