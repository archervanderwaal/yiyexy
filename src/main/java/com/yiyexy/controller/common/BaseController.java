package com.yiyexy.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
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
