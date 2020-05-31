package com.yun.springboot.exception;

import com.yun.springboot.model.result.RetResponseUtil;
import com.yun.springboot.model.result.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger gmm_request_error_logger = LoggerFactory.getLogger("gmm_request_error_logger");

    @ExceptionHandler(value = SignalingException.class)
    @ResponseBody
    public RetResult signalingHandler(HttpServletRequest req, SignalingException e) {
        gmm_request_error_logger.error("SignalingException :", e);
        return RetResponseUtil.makeRsp(e.errorCode);
    }

}