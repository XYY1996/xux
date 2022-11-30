package com.xux.imc.template.common;


public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BaseException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public BaseException(int errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }
}
