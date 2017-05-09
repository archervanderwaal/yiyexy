package com.yiyexy.service.car;

import com.yiyexy.exception.MemberUserIsFullException;
import com.yiyexy.model.common.User;

import java.util.List;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 成员服务接口
 */
public interface IMemberService {

    /**
     * 取消用户uid在iid拼车的行程信息
     * @param iid
     * @param uid
     * @return
     */
    boolean cancelStroke (int iid, int uid);

    /**
     * 根据拼车信息查询所有成员的信息
     * @param iid
     * @return
     */
    List<User> getMemberInfos(int iid);

    /**
     * 添加用户uid到行程中去
      * @param iid
     * @param uid
     */
    void addUserToStroke(int iid, int uid) throws MemberUserIsFullException;
}
