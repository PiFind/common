package io.pifind.common.exception;

/**
 * 服务器运行时异常（不进行检查，直接抛出）
 */
public class ServerRuntimeException extends RuntimeException implements IStatusCodeException{

    /**
     * 错误码
     */
    private final int code;

    public ServerRuntimeException(int code,String message) {
        super(code + ":" + message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
