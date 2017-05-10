package com.yiyexy.service.car;

import com.yiyexy.model.car.CarInformation;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 维护拼车信息的服务接口
 */
public interface ICarInformationService {

    /**
     * 添加一个行程信息
     * @param carInformation
     * @return 主键
     */
    int addCarInformation(CarInformation carInformation);

    /**
     * 根据发布日期查询拼车信息
     * @param pubTime
     * @return 发布日期为pubTime的所有行程信息
     */
    List<CarInformation> getCarInformationsByPubTime(Date pubTime);

    /**
     * 根据开始日期查询所有的行程信息
     * @param startDate
     * @return
     */
    List<CarInformation> getCarInformationsByStartDate(Date startDate);

    /**
     * 根据用户查询用户所参加或者发起的拼车信息
     * @param uid
     * @return
     */
    List<CarInformation> getUserSignedUpCarInformation(int uid);

    /**
     * 发布拼车信息
     * @param carInformation
     * @return
     */
    Map<String, String> createStroke(CarInformation carInformation);
}
