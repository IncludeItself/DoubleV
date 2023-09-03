package com.wuxinfeng.doublev.fi.exception;

import com.wuxinfeng.common.exception.BizCodeEnum;
import com.wuxinfeng.common.utils.R;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * @BelongsProject: DoubleV
 * @BelongsPackage: com.wuxinfeng.doublev.fi.exception
 * @Author: yanhongwei
 * @CreateTime: 2023-03-27  14:16
 * @Description: TODO
 * @Version: 1.0
 */
@RestControllerAdvice(basePackages = "com.wuxinfeng.doublev.fi")
public class FiControllerAdvice {

    @ExceptionHandler(value = ValidationException.class)
    public R handleException(ValidationException e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e){
        for (FieldError error:e.getFieldErrors()) {
            System.out.println("错误字段："+error.getField());
            System.out.println(error.getCode()+error.getDefaultMessage());
        }
        e.printStackTrace();
        return R.error("字段不在可选范围内");
    }

    @ExceptionHandler(value = DuplicateWriteOffException.class)
    public R handleException(DuplicateWriteOffException e){
        e.printStackTrace();
        return R.error("不能冲销已冲销的凭证");
    }

    @ExceptionHandler(value = WriteOffCleardException.class)
    public R handleException(WriteOffCleardException e){
        e.printStackTrace();
        return R.error("冲销凭证包含已结清项目");
    }
    @ExceptionHandler(value = Exception.class)
    public R handleException(Throwable throwable){
        throwable.printStackTrace();
        return R.error();
    }


}
