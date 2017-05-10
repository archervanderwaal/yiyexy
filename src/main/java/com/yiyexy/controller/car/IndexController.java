package com.yiyexy.controller.car;


import com.yiyexy.annotation.*;
import com.yiyexy.constant.CarInformationConstant;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.IndexConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.controller.common.BaseController;
import com.yiyexy.model.car.CarInformation;
import com.yiyexy.model.common.User;
import com.yiyexy.service.car.impl.CarInformationService;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.result.Result;
import com.yiyexy.util.result.ResultBuilder;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * _____ _                      ___  ___
 * /  ___| |                     |  \/  |
 * \ `--.| |_ ___  _ __ _ __ ___ | .  . | __ _
 * `--. \ __/ _ \| '__| '_ ` _ \| |\/| |/ _` |
 * /\__/ / || (_) | |  | | | | | | |  | | (_| |
 * \____/ \__\___/|_|  |_| |_| |_\_|  |_/\__,_| 我想念你，一如独自撸码的忧伤...., 重构代码就像是再婚，看到那个恶心的自己和恶心的对方，依旧是恶心的心情.
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 * @description: 拼车主页控制器类
 */
@Api(value = "/api/index", description = IndexConstant.INDEX_CONTROLLER_DESC)
@RequestMapping(value = "/api/index")
@RestController
public class IndexController extends BaseController {

    @Autowired
    private CarInformationService carInformationService;

    /**
     * 根据开始日期查询所有的拼车信息
     * @param startDate
     * @return
     */
    @ApiOperation(value = IndexConstant.INDEX_METHOD_DESC, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "startDate", value = CarInformationConstant.START_DATE_DESC, required = true, paramType = "path")})
    @GetMapping("/{startDate}")
    public Result<List<CarInformation>> handleIndexData(@PathVariable Date startDate) {

        List<CarInformation> carInformations;
        carInformations = carInformationService.getCarInformationsByStartDate(startDate);
        return ResultBuilder.success(carInformations);
    }

    /**
     * 发起拼车
     * @param carInformation
     * @param loginUser
     * @return
     */
    @com.yiyexy.annotation.Authorization
    @ApiOperation(value = CarInformationConstant.ADD_CAR_INFORMATION_METHOD_DESC, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true, paramType = "header")})
    @PostMapping(value = "/create/stroke")
    public Result<Object> createStroke(@RequestBody CarInformation carInformation, @ApiIgnore @CurrentUser User loginUser) {

        carInformation.setUid(loginUser.getUid());
        Map<String, String> datas = carInformationService.createStroke(carInformation);
        if (!ObjectUtil.isEmpty(datas.get(CommonConstant.SUCCESS))) {
            return ResultBuilder.success(datas.get(CommonConstant.SUCCESS));
        } else {
            return ResultBuilder.fail(datas.get(CommonConstant.FAIL));
        }
    }
}
