package com.wuxinfeng.doublev.fi.validator;

import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.wuxinfeng.doublev.fi.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-03-29  09:56
 */
@RequiredArgsConstructor
public class CurrencyOptionalConstraint implements ConstraintValidator<CurrencyOptional, String> {

    private final CurrencyRateService currencyRateService;
    @Override
    public void initialize(CurrencyOptional constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return currencyRateService.getOptions().contains(s);
    }
}
