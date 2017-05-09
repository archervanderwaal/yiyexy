package com.yiyexy.controller.car;

import com.yiyexy.annotation.Authorization;
import com.yiyexy.annotation.CurrentUser;
import com.yiyexy.constant.CarInformationConstant;
import com.yiyexy.constant.MemberConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.controller.common.BaseController;
import com.yiyexy.model.common.User;
import com.yiyexy.service.car.IMemberService;
import com.yiyexy.service.car.impl.CarInformationService;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * _____ _                      ___  ___
 * /  ___| |                     |  \/  |
 * \ `--.| |_ ___  _ __ _ __ ___ | .  . | __ _
 * `--. \ __/ _ \| '__| '_ ` _ \| |\/| |/ _` |
 * /\__/ / || (_) | |  | | | | | | |  | | (_| |
 * \____/ \__\___/|_|  |_| |_| |_\_|  |_/\__,_| 我想念你，一如独自撸码的忧伤....
 * 凌晨12点43分，此刻我在异乡的床上，抱着电脑，写着自己的代码，我很享受这种感觉，但是我更想你。
 * <p>Created on 2017/5/10.</p>
 *
 * @author stormma
 * @description: 成员信息控制器类
 */
@Api(value = "/api/member", description = MemberConstant.MEMBER_CONTROLLER_DESC)
@RestController
@RequestMapping("/api/member")
public class MemberController extends BaseController {

    @Autowired
    private CarInformationService carInformationService;

    @Autowired
    private IMemberService memberService;

    /**
     * 获得加入拼车信息的成员信息(此处要登录)
     *
     * @param iid
     * @return
     */
    @Authorization
    @ApiOperation(value = MemberConstant.GET_MEMBER_METHOD_DESC, httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "iid", value = CarInformationConstant.IID_FIELD_DESC, required = true, paramType = "path"),
            @ApiImplicitParam(name = "authorization", value = UserConstant.AUTHORIZATION_TOKEN, required = true, paramType = "header")})
    @GetMapping("/{iid}")
    public Result<List<User>> getMember(@PathVariable int iid, @ApiIgnore @CurrentUser User loginUser) {

        List<User> users = memberService.getMemberInfos(iid);

        return ResultBuilder.success(users);
    }
}
