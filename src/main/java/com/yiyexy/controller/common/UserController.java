package com.yiyexy.controller.common;

import com.yiyexy.annotation.Authorization;
import com.yiyexy.annotation.CurrentUser;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.constant.ValidateConstant;
import com.yiyexy.manager.TokenManager;
import com.yiyexy.model.common.TokenModel;
import com.yiyexy.model.common.User;
import com.yiyexy.service.common.IMessageService;
import com.yiyexy.service.common.IUserService;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.result.Result;
import com.yiyexy.util.result.ResultBuilder;
import io.swagger.annotations.*;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 *
 *  _____ _                      ___  ___
 /  ___| |                     |  \/  |
 \ `--.| |_ ___  _ __ _ __ ___ | .  . | __ _
 `--. \ __/ _ \| '__| '_ ` _ \| |\/| |/ _` |
 /\__/ / || (_) | |  | | | | | | |  | | (_| |
 \____/ \__\___/|_|  |_| |_| |_\_|  |_/\__,_| 我想念你，一如独自撸码的忧伤....
 *
 *
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

    @Autowired
    private IMessageService messageService;


    /**
     * 用户登录
     * @param paramUser
     * @return
     */
    @ApiOperation(value = UserConstant.LOGIN_DESC, httpMethod = "POST")
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestBody User paramUser) {


        Map<String, Integer> datas = userService.isLoginSuccess(paramUser.getMobile(), paramUser.getPassword());

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
     * @param paramUser
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.UPDATE_PASSWORD, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
                        , paramType = "header")})
    @PostMapping(value = "/update/password")
    public Result<Object> updatePassword(@ApiIgnore @CurrentUser User loginUser,
                                         @RequestBody User paramUser) {

        String mobile = loginUser.getMobile();

        boolean result = userService.updatePassword(mobile, paramUser.getPassword());
        if (result) {
            return ResultBuilder.success();
        }
        return ResultBuilder.fail();
    }

    /**
     * 修改qq信息
     * @param loginUser
     * @param qq
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.UPDATE_QQ_NUM, httpMethod = "PUT")
    @ApiImplicitParams({@ApiImplicitParam(name = "qq", value = UserConstant.USER_QQ_NUM, required = true
                        , paramType = "path")
                        , @ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
                        , paramType = "header")})
    @PutMapping(value = "/update/qq/{qq}")
    public Result<Object> updateQQnum(@ApiIgnore @CurrentUser User loginUser,
                                      @PathVariable String qq) {

        String mobile = loginUser.getMobile();

        boolean result = userService.updateQQNum(mobile, qq);

        if (result) {
            return ResultBuilder.success();
        }
        return ResultBuilder.fail();
    }

    /**
     * <p>修改用户名</p>
     * @param loginUser
     * @param userName
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.UPDATE_USER_NAME, httpMethod = "PUT")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = UserConstant.USER_NAME_DESC, required = true
                        , paramType = "path")
                        , @ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
                        , paramType = "header")})
    @PutMapping(value = "/update/username/{userName}")
    public Result<Object> updateUserName(@ApiIgnore @CurrentUser User loginUser,
                                         @PathVariable String userName) {
        String mobile = loginUser.getMobile();
        String result = userService.updateUserName(mobile, userName);

        //修改成功
        if (result.equals(CommonConstant.SUCCESS)) {
            return ResultBuilder.success();
        } else {
            return ResultBuilder.fail(result);
        }
    }

    /**
     * 重置密码
     * @param validateCode
     * @param paramUser
     * @return
     */
    @ApiOperation(value = UserConstant.RESET_PASSWORD_METHOD_DESC, httpMethod = "PUT")
    @ApiImplicitParams({@ApiImplicitParam(name = "validateCode", value = ValidateConstant.USER_COMMIT_VALIDATE_CODE, required = true, paramType = "path")})
    @PutMapping("/reset/password/{validateCode}")
    public Result<Object> resetPassword(@PathVariable String validateCode,
                                            @RequestBody User paramUser) {

        //校验验证码
        if (!messageService.checkValidateIsValid(validateCode, paramUser.getMobile())) {
            return ResultBuilder.fail(ValidateConstant.VALIDATE_CODE_IS_INVALID);
        }
        //开始更新密码
        boolean result = userService.updatePassword(paramUser.getMobile(), paramUser.getPassword());
        if (result) {
            return ResultBuilder.success();
        }
        return ResultBuilder.fail();
    }

    /**
     * 注册接口
     * @param validateCode
     * @param paramUser
     * @return
     */
    @ApiOperation(value = UserConstant.USER_REGISTER_METHOD_DESC, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "validateCode", value = ValidateConstant.USER_COMMIT_VALIDATE_CODE, required = true, paramType = "path")})
    @PostMapping(value = "/register/{validateCode}")
    public Result<Object> register(@PathVariable String validateCode,
                                   @RequestBody User paramUser) {
        //校验验证码
        if (!messageService.checkValidateIsValid(validateCode, paramUser.getMobile())) {
            return ResultBuilder.fail(ValidateConstant.VALIDATE_CODE_IS_INVALID);
        }

        Map<String, String> datas = userService.register(paramUser.getMobile(), paramUser.getPassword());
        if (!ObjectUtil.isEmpty(datas.get(CommonConstant.SUCCESS))) {
            return ResultBuilder.success(datas.get(CommonConstant.SUCCESS));
        } else {
            return ResultBuilder.fail(datas.get(CommonConstant.FAIL));
        }
    }

    /**
     * 获得登录用户的个人信息
     * @param loginUser
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.GET_USER_INFO_METHOD_DESC, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
            , paramType = "header")})
    @GetMapping("/info")
    public Result<User> getUserInfo(@ApiIgnore @CurrentUser User loginUser) {
        return ResultBuilder.success(loginUser);
    }
}