package com.yiyexy.service.car.impl;

import com.yiyexy.dao.car.CarInformationDao;
import com.yiyexy.model.car.CarInformation;
import com.yiyexy.service.car.ICarInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <P>Created on 2017/5/9.</P>
 *
 * @author stormma
 *
 * @description: 拼车信息的服务实现
 */
@Service
public class CarInformationService implements ICarInformationService {

    @Autowired
    private CarInformationDao carInformationDao;

    /**
     * 添加一个行程信息
     * @param carInformation
     * @return 主键
     */
    @Override
    public int addCarInformation(CarInformation carInformation) {
        carInformationDao.insertCarInformation(carInformation);
        return carInformation.getIid();
    }

    /**
     * 根据发布日期查询拼车信息
     * @param pubTime
     * @return 发布日期为pubTime的所有行程信息
     */
    @Override
    public List<CarInformation> getCarInformationsByPubTime(Date pubTime) {
        return carInformationDao.getCarInformationsByPubTime(pubTime);
    }

    /**
     * 根据开始日期查询所有的行程信息
     * @param startDate
     * @return
     */
    @Override
    public List<CarInformation> getCarInformationsByStartDate(Date startDate) {
        return carInformationDao.getCarInformationsByStartDate(startDate);
    }
}
