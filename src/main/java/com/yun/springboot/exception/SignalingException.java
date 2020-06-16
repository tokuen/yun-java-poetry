package com.yun.springboot.exception;


import com.yun.springboot.model.result.ErrorCode;
/**
 * 自定义异常
 */
public class SignalingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorCode errorCode;

    public SignalingException(ErrorCode errorCode) {
        super(String.valueOf(errorCode.code));
        this.errorCode = errorCode;
    }

    public SignalingException(ErrorCode errorCode, Throwable cause) {
        super(String.valueOf(errorCode), cause);
        this.errorCode = errorCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}