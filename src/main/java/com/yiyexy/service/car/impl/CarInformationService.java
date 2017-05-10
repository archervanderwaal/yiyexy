package com.yiyexy.service.car.impl;

import com.yiyexy.constant.CarInformationConstant;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.MemberConstant;
import com.yiyexy.dao.car.CarInformationDao;
import com.yiyexy.model.car.CarInformation;
import com.yiyexy.service.car.ICarInformationService;
import com.yiyexy.util.DateUtils;
import com.yiyexy.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户查询用户所参加或者发起的拼车信息
     *
     * @param uid
     * @return
     */
    @Override
    public List<CarInformation> getUserSignedUpCarInformation(int uid) {
        return carInformationDao.getAllCarInfomationByUser(uid);
    }

    /**
     * 发布拼车信息
     * @param carInformation
     * @return
     */
    @Override
    public Map<String, String> createStroke(CarInformation carInformation) {
        Map<String, String> datas = new HashMap<>();
        carInformation.setPubTime(new Date());
        //先查询已有的拼车信息
        List<CarInformation> carInformations = carInformationDao.getAllCarInfomationByUser(carInformation.getUid());
        Date startDate = carInformation.getStartDate();
        String startPos = carInformation.getStartPos();
        if (ObjectUtil.isEmpty(startDate) || StringUtils.isEmpty(startPos)) {
            datas.put(CommonConstant.FAIL, CommonConstant.PARAM_BIND_FAILED);
        }
        for (CarInformation information : carInformations) {
            //如果出发时间相同
            if (DateUtils.getDifferenceDays(information.getStartDate(), startDate) == 0) {
                //如果开始地点相同
                if (information.getStartPos().equals(startPos)) {
                    datas.put(CommonConstant.FAIL, MemberConstant.INVAILD_SIGN_UP);
                    return datas;
                }
            }
        }

        //可以发布拼车信息
        carInformationDao.insertCarInformation(carInformation);
        if (!ObjectUtil.isEmpty(carInformation.getIid())) {
            datas.put(CommonConstant.SUCCESS, CommonConstant.SUCCESS);
            return datas;
        }
        datas.put(CommonConstant.FAIL, CarInformationConstant.CREATE_STROKE_FAILED);
        return datas;
    }
}
