package com.wuxinfeng.doublev.mm.exception;

import com.wuxinfeng.common.utils.R;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.wuxinfeng.common.exception.BizCodeEnum.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-04-10  11:00
 */
@RestControllerAdvice(basePackages = "com.wuxinfeng.doublev.mm")
public class MMControllerAdvice {

    @ExceptionHandler(NoCurrentPeriodException.class)
    public R handleException(NoCurrentPeriodException e){
        System.out.println("捕获exception成功");
        e.printStackTrace();
        return R.error(NO_CURRENT_PERIOD_EXCEPTION.getCode(),NO_CURRENT_PERIOD_EXCEPTION.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e){
        System.out.println("捕获exception成功");
        e.printStackTrace();
        String message="";
        for (FieldError fieldError : e.getFieldErrors()) {
            message+=fieldError.getField()+":"+fieldError.getDefaultMessage()+";";
        }
        return R.error(METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode(),message);
    }

    @ExceptionHandler(PurchaseOrderMissingException.class)
    public R handleException(PurchaseOrderMissingException throwable){
        System.out.println("捕获exception成功");
        throwable.printStackTrace();
        return R.error(PURCHASE_ORDER_MISSING_EXCEPTION.getCode(),PURCHASE_ORDER_MISSING_EXCEPTION.getMsg());
    }

    //UnitMissingException
    @ExceptionHandler(UnitMissingException.class)
    public R handleException(UnitMissingException throwable){
        System.out.println("捕获exception成功");
        throwable.printStackTrace();
        return R.error(UNIT_MISSING_EXCEPTION.getCode(),UNIT_MISSING_EXCEPTION.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Throwable throwable){
        throwable.printStackTrace();
        return R.error();
    }
}
