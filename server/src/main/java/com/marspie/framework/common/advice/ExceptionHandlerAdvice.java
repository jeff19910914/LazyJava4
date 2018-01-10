package com.marspie.framework.common.advice;

import com.marspie.framework.common.web.Result;
import com.marspie.framework.common.web.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 统一异常处理类
 *
 * @author alex.yao
 * @version 1.0.0
 * @create 2018-01-10 15:23
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return ResultUtil.warn(ResultCode.PARAMETER_ERROR, tips);
    }

//    @ExceptionHandler(ResultException.class)
//    public Result handleResultException(ResultException e, HttpServletRequest request) {
//        logger.debug("uri={} | requestBody={}", request.getRequestURI(),
//                JSON.toJSONString(UserController.MODEL_HOLDER.get()));
//        return ResultUtils.warn(e.getResultCode());
//    }

//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e, HttpServletRequest request) {
//        logger.error("uri={} | requestBody={}", request.getRequestURI(),
//                JSON.toJSONString(UserController.MODEL_HOLDER.get()), e);
//        return ResultUtil.warn(ResultCode.WEAK_NET_WORK);
//    }

}
