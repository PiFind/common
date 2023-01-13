package io.pifind.common.exception;

/**
 * 服务器异常
 */
public class ServerException extends Throwable {

    /**
     * 错误码
     */
    private final int code;

    public ServerException(int code,String message) {
        super(code + ":" + message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
