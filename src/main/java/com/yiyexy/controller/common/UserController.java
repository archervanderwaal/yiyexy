package com.yiyexy.controller.common;

import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.manager.TokenManager;
import com.yiyexy.model.common.TokenModel;
import com.yiyexy.service.common.IUserService;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.result.Result;
import com.yiyexy.util.result.ResultBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户控制器</p>
 */
@Api(value = "/api/user", description = UserConstant.CONTROLLER_DESC)
@RestController
@RequestMapping(value = "/api/user")
public class UserController  extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenManager tokenManager;


    @ApiOperation(value = UserConstant.LOGIN_DESC, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = UserConstant.USER_ID_DESC, paramType = "query"),
                        @ApiImplicitParam(name = "password", value = UserConstant.USER_PASSWORD_DESC, paramType = "query")})
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestParam("mobile")String mobile, @RequestParam("password")String password) {


        Map<String, Integer> datas = userService.isLoginSuccess(mobile, password);

        //如果登录失败
        if (!ObjectUtil.isEmpty(datas.get(CommonConstant.FAIL))) {
            Integer status = datas.get(CommonConstant.FAIL);
            return ResultBuilder.fail(status + "");
        }

        Integer uid = datas.get(CommonConstant.SUCCESS);
        TokenModel tokenModel = tokenManager.createToken(uid);
        return ResultBuilder.success(tokenModel);
    }
}