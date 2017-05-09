package com.yiyexy.dao.car;

import com.yiyexy.model.car.CarInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 拼车信息 dao 层接口
 */
@Mapper
public interface CarInformationDao {

    /**
     * 添加形成dao层接口
     * @param carInformation
     */
    void insertCarInformation(@Param("carInfo") CarInformation carInformation);

    /**
     * 删除制定id的拼车信息
     * @param iid
     */
    void removeCarInformation(@Param("iid") int iid);

    /**
     * 根据 iid 查询拼车信息
     * @param iid
     * @return
     */
    CarInformation getCarInformation(@Param("iid") int iid);


    /**
     * 查询所有的拼车行程
     * @return
     */
    List<CarInformation> getAllCarInformation();


    /**
     * 根据发布时间查询行程信息
     * @param pubTime
     * @return
     */
    List<CarInformation> getCarInformationsByPubTime(@Param("pubTime") Date pubTime);

    /**
     * 根据用户 id 查询用户自己的行程(既包括自己发起的，还包括参与的 )
     * @param uid
     * @return
     */
    List<CarInformation> getAllCarInfomationByUser(@Param("uid") Integer uid);

    /**
     * 根据开始日期查询所有的行程
     * @param startDate
     * @return
     */
    List<CarInformation> getCarInformationsByStartDate(@Param("startDate") Date startDate);
}
