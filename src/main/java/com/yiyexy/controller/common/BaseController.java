package com.yiyexy.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * *  _____ _                      ___  ___
 /  ___| |                     |  \/  |
 \ `--.| |_ ___  _ __ _ __ ___ | .  . | __ _
 `--. \ __/ _ \| '__| '_ ` _ \| |\/| |/ _` |
 /\__/ / || (_) | |  | | | | | | |  | | (_| |
 \____/ \__\___/|_|  |_| |_| |_\_|  |_/\__,_| 我想念你，一如独自撸码的忧伤....

 * <p>Create On 20175/9</p>
 *
 * @author stormma
 *
 * 控制器基类
 */
@RestController
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletResponse response;

}
