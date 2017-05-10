package com.yiyexy.controller.common;

import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.constant.ValidateConstant;
import com.yiyexy.service.common.IUserService;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.result.Result;
import com.yiyexy.util.result.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * _____ _                      ___  ___
 * /  ___| |                     |  \/  |
 * \ `--.| |_ ___  _ __ _ __ ___ | .  . | __ _
 * `--. \ __/ _ \| '__| '_ ` _ \| |\/| |/ _` |
 * /\__/ / || (_) | |  | | | | | | |  | | (_| |
 * \____/ \__\___/|_|  |_| |_| |_\_|  |_/\__,_| 我想念你，一如独自撸码的忧伤...
 * <p>
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 * @description: 验证码服务类
 */
@Api(value = "/api/validate", description = ValidateConstant.VALIDATE_CONTROLLER_DESC)
@RestController
@RequestMapping("/api/validate")
public class ValidateController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = ValidateConstant.VALIDATE_SEND_METHOD_DESC, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = UserConstant.MOBILE_DESC, paramType = "path", required = true),
            @ApiImplicitParam(name = "type", value = CommonConstant.VALIDATE_TYPE_DESC, paramType = "path", required = true)})
    @GetMapping(value = "/code/{type}/{mobile}")
    public Result<Object> sendValidateCode(@PathVariable String mobile,
                                           @PathVariable int type) {
        //发送验证码
        Map<String, String> datas = userService.sendValidateCode(mobile, type);
        //发送成功
        if (!ObjectUtil.isEmpty(datas.get(CommonConstant.SUCCESS))) {
            return ResultBuilder.success();
        } else {
            return ResultBuilder.fail(datas.get(CommonConstant.FAIL));
        }
    }
}
