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

    public static final String USER_COMMIT_VALIDATE_CODE = "用户提交的验证码";

    //设置多少天之内发送验证码数目有限
    public static final long VALIDATE_CHECK_DAYS = 7L;

    public static final String VALIDATE_REDIS_SUFFIX = "_count";

    public static final int RESET_PASSWORD_VALIDATE_CODE = 1;

    public static final int REGISTER_VALIDATE_CODE = 0;

    public static final String THIS_MOBILE_ALERDY_REGISTER = "该手机号码已经注册";

    public static final String VALIDATE_CODE_IS_INVALID = "验证码校验不通过，无法继续这次操作，请重试";
}
