package com.company.common.exception.handler;


import com.company.common.exception.GlobalException;
import com.company.common.model.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 全局异常处理
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, GlobalException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getErrorInfo().getMessage());
        r.setCode(e.getErrorInfo().getCode());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

}
