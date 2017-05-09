package com.yiyexy.dao.car;

import com.yiyexy.model.car.CarInformation;
import com.yiyexy.util.NumUtil;
import com.yiyexy.util.log.MyLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 拼车信息 dao 层测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarInformationDaoTest {

    @Autowired
    private CarInformationDao carInformationDao;

    private static Logger LOGGER = MyLogger.getLogger(CarInformationDaoTest.class);

    @Test
    public void testInsertCarInformation() {

        CarInformation carInformation = new CarInformation();
        carInformation.setUid(55);
        carInformation.setStartDate(new Date());
        carInformation.setStartPos("我爱你");
        carInformation.setArrivePos("你爱我");
        carInformation.setCurtMember(2);
        carInformation.setMaxMember(6);
        carInformation.setMessage("一辈子");
        carInformation.setPubTime(new Date());
        carInformation.setStartTimeMinHour(NumUtil.numToString(12));
        carInformation.setStartTimeMinMin(NumUtil.numToString(12));
        carInformation.setStartTimeMaxHour(NumUtil.numToString(18));
        carInformation.setStartTimeMaxMin(NumUtil.numToString(23));
        carInformationDao.insertCarInformation(carInformation);

        LOGGER.info("测试结果:{}", carInformation.getIid());
    }

    @Test
    public void testGetCarInformation() {
        CarInformation carInformation = carInformationDao.getCarInformation(293);

        LOGGER.info("测试结果:{}", carInformation);
    }

    @Test
    public void testGetAllCarInformation() {

        List<CarInformation> carInformations = carInformationDao.getAllCarInformation();

        LOGGER.info("测试结果:{}", carInformations);
    }

    @Test
    public void testGetCarInformationsByPubTime() {

        List<CarInformation> carInformations = carInformationDao.getCarInformationsByPubTime(new Date());

        LOGGER.info("测试结果:{}", carInformations);
    }

    @Test
    public void testGetAllCarInformationByUser() {

        int uid = 55;

        List<CarInformation> carInformations = carInformationDao.getAllCarInfomationByUser(uid);

        LOGGER.info("测试结果:{}", carInformations);
    }

    @Test
    public void testGetCarInformationsByStartDate() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CarInformation> carInformations = carInformationDao.getCarInformationsByStartDate(simpleDateFormat.parse("2017-05-08"));
        LOGGER.info("测试结果:{}", carInformations);
    }
}
