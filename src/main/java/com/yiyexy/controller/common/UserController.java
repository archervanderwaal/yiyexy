package com.yiyexy.controller.common;

import com.yiyexy.annotation.Authorization;
import com.yiyexy.annotation.CurrentUser;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.manager.TokenManager;
import com.yiyexy.model.common.TokenModel;
import com.yiyexy.model.common.User;
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
import springfox.documentation.annotations.ApiIgnore;

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


    /**
     * 用户登录
     * @param mobile
     * @param password
     * @return
     */
    @ApiOperation(value = UserConstant.LOGIN_DESC, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "mobile", value = UserConstant.MOBILE_DESC, paramType = "query"),
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


    /**
     * 修改密码
     * @param loginUser
     * @param password
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.UPDATE_PASSWORD, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "password", value = UserConstant.USER_PASSWORD_DESC, required = true
                        ,paramType = "query"),
                        @ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
                        , paramType = "header")})
    @PostMapping(value = "/update/password")
    public Result<Object> updatePassword(@ApiIgnore @CurrentUser User loginUser,
                                         @RequestParam("password")String password) {

        String mobile = loginUser.getMobile();

        boolean result = userService.updatePassword(mobile, password);
        if (result) {
            return ResultBuilder.success();
        }
        return ResultBuilder.fail();
    }
}