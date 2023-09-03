package com.wuxinfeng.doublev.mm.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-08-29  13:16
 */
public class NotZeroConstraint implements ConstraintValidator<NotZero,Number> {
    @Override
    public void initialize(NotZero constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number a, ConstraintValidatorContext constraintValidatorContext) {
        if (a instanceof Float) {
            return (Float) a !=0;
        }
        return false;
    }
}
