package com.yiyexy.dao.common;

import com.yiyexy.model.common.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户 dao 层接口</p>
 */
@Mapper
public interface UserDao {


    /**
     * <p>根据手机号和密码查询用户</p>
     * @param user
     * @return
     */
    User getUser(User user);
}
