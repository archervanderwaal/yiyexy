package com.yiyexy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>Create on 2017/5/7</p>
 *
 * @author stormma
 *
 * @description: <p>Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象</p>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
