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
import io.swagger.annotations.*;
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


    /**
     * 用户登录
     * @param user
     * @return
     */
    @ApiOperation(value = UserConstant.LOGIN_DESC, httpMethod = "POST")
    @PostMapping(value = "/login")
    public Result<Object> login(@RequestBody User user) {


        Map<String, Integer> datas = userService.isLoginSuccess(user.getMobile(), user.getPassword());

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
     * @param user
     * @return
     */
    @Authorization
    @ApiOperation(value = UserConstant.UPDATE_PASSWORD, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true
                        , paramType = "header")})
    @PostMapping(value = "/update/password")
    public Result<Object> updatePassword(@ApiIgnore @CurrentUser User loginUser,
                                         @RequestBody User user) {

        String mobile = loginUser.getMobile();

        boolean result = userService.updatePassword(mobile, user.getPassword());
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

    //重置密码

}