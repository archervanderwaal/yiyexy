package com.yiyexy.handle;

import com.yiyexy.util.result.Result;
import com.yiyexy.util.result.ResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Created on 2017/3/15.</p>
 *
 * @author StormMa
 *
 * @Description: 统一异常处理
 */
@RestControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandle(Exception e) {
        if (e instanceof RuntimeException) {
            logger.error("服务器发生错误：{}", e);
            return ResultBuilder.fail("服务器内部错误");
        }

        logger.error("错误信息:{}", e);
        return ResultBuilder.fail();
    }
}
