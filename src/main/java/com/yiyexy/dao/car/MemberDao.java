package com.yiyexy.dao.car;

import com.yiyexy.model.car.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 成员 dao 层接口
 */
@Mapper
public interface MemberDao {


    /**
     * 增加用户到行程中
     * @param member
     */
    void addUserToMember(@Param("member") Member member);

    /**
     * 查询拼车信息的成员
     * @param iid
     * @return
     */
    Member getMember(@Param("iid") int iid);

    /**
     * 删除成员中的某个用户
     * @param member
     */
    void removeUserFromMember(@Param("member") Member member);
}
