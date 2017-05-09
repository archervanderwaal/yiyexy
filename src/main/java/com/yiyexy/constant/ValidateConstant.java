package com.yiyexy.constant;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 验证码常量池
 */
public class ValidateConstant {

    public static final String VALIDATE_CONTROLLER_DESC = "验证码控制器类";

    public static final String VALIDATE_SEND_METHOD_DESC = "发送验证码";

    public static final String VALIDATE_SEND_FAIL = "验证码发送失败";

    public static final String VALIDATE_SEND_MAX_COUNT_CODE = "一周发送验证码已达上限";

    public static final long VALIDATE_CODE_VALID_TIME_MINUNTES = 30L;

    //设置多少天之内发送验证码数目有限
    public static final long VALIDATE_CHECK_DAYS = 7L;

    public static final String VALIDATE_REDIS_SUFFIX = "_count";
}
