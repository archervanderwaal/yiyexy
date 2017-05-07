package com.yiyexy.resolvers;

import com.yiyexy.annotation.CurrentUser;
import com.yiyexy.constant.TokenConstant;
import com.yiyexy.dao.common.UserDao;
import com.yiyexy.model.common.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * <p>Create on 2017/5/7</p>
 *
 * @author stormma
 *
 * @description: <p>增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户</p>
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是User并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(User.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        Integer currentUserId = (Integer) webRequest.getAttribute(TokenConstant.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
            //从数据库中查询并返回
            return userDao.getUserById(currentUserId);
        }
        throw new MissingServletRequestPartException(TokenConstant.CURRENT_USER_ID);
    }
}
