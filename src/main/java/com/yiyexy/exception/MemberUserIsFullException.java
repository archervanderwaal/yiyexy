package com.yiyexy.exception;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 成员已满异常
 */
public class MemberUserIsFullException extends Exception {

    public MemberUserIsFullException() {
    }

    public MemberUserIsFullException(String message) {
        super(message);
    }

    public MemberUserIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberUserIsFullException(Throwable cause) {
        super(cause);
    }

    public MemberUserIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
