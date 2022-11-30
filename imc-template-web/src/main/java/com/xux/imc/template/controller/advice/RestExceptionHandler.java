package com.xux.imc.template.controller.advice;


import com.xux.imc.template.api.rest.Result;
import com.xux.imc.template.common.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail();
    }

    /**
     * Assert异常
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(IllegalArgumentException e) {
        return Result.fail();
    }


    /**
     * 抓取自定义异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(BaseException e) {
        return Result.fail();
    }


    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Result<String>> handleValidatedException(Exception e) {
        Result<String> resp = null;

//        if (e instanceof MethodArgumentNotValidException) {
//            // BeanValidation exception
//            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
//            resp = Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()),
//                    ex.getBindingResult().getAllErrors().stream()
//                            .map(ObjectError::getDefaultMessage)
//                            .collect(Collectors.joining("; "))
//                    );
//        } else if (e instanceof ConstraintViolationException) {
//            // BeanValidation GET simple param
//            ConstraintViolationException ex = (ConstraintViolationException) e;
//            resp = Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()),
//                    ex.getConstraintViolations().stream()
//                            .map(ConstraintViolation::getMessage)
//                            .collect(Collectors.joining("; "))
//                  );
//        } else if (e instanceof BindException) {
//            // BeanValidation GET object param
//            BindException ex = (BindException) e;
//            resp = Result.fail(String.valueOf(HttpStatus.BAD_REQUEST.value()),
//                    ex.getAllErrors().stream()
//                            .map(ObjectError::getDefaultMessage)
//                            .collect(Collectors.joining("; "))
//                    );
//        }

        log.error("参数校验异常:{}", resp.getMsg());
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

}