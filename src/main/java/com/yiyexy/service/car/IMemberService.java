package com.yiyexy.service.car;

import com.yiyexy.model.common.User;

import java.util.List;
import java.util.Map;

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
    Map<String, String> cancelStroke (int iid, int uid);

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
     * @return
     */
    Map<String, String> addUserToStroke(int iid, int uid);

    /**
     * 判断uid是否可以报名iid这个行程信息
     * @param uid
     * @param iid
     * @return
     */
    Map<String, String> isCanSignUpCarInformation(int uid, int iid);
}
