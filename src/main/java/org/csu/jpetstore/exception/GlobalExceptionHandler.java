package org.csu.jpetstore.exception;

import org.csu.jpetstore.utils.ResultFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 全局异常处理
 * Version： V1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultFactory handleException(Exception e) {
        String msg = e.getMessage();
//        if (msg == null || msg.equals(""))

        if (msg.equals("无token，请重新登录"))
            return ResultFactory.failedResult(40001,msg);
        if (msg.equals("token 无效，请重新获取"))
            return  ResultFactory.failedResult(40001,msg);
        return ResultFactory.failedResult(500,"服务器内部错误！");
    }
}