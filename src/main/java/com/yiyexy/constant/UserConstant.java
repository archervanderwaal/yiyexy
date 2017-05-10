package com.yiyexy.constant;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户常量池</p>
 */
public class UserConstant {

    public static final String USER_ENTITY_DESC = "用户实体类";

    public static final String USER_ID_DESC = "用户id";

    public static final String USER_QQ_NUM = "用户qq号码";

    public static final String USER_NAME_DESC = "用户名";

    public static final String USER_PASSWORD_DESC = "用户密码";

    public static final String LOGIN_DESC = "用户登录(参数: mobile、password)";

    public static final String CONTROLLER_DESC = "处理用户数据的请求";

    public static final String UPDATE_PASSWORD = "修改用户密码(password传到user对象中, 授权码头参数)";

    public static final String UPDATE_USER_NAME = "修改用户名";

    public static final String CAN_NOT_UPDATE_USER_NAME = "不能修改用户名";

    public static final String UPDATE_QQ_NUM = "更新qq号码";

    public static final String MOBILE_DESC = "用户手机号码";

    public static final String AUTHORIZATION_TOKEN = "授权码(格式: uid_token)";

    public static final String RESET_PASSWORD_METHOD_DESC = "重置密码接口(参数: mobile, password body参数, validateCode 路径参数)";
    
    public static final int NO_THIS_MOBILE = -1;

    public static final int ERROR_PASSWORD = 0;

    public static final int LOGIN_SUCCESS = 1;

    public static final String USER_REGISTER_METHOD_DESC = "用户注册(参数:mobile, password body参数, validateCode:路径参数)";

    public static final String GET_USER_INFO_METHOD_DESC = "获得用户个人信息接口";
}
