package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.DataDicEnum;
import com.wuxinfeng.doublev.mm.service.DataDicService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-09-02  11:00
 */
@RequiredArgsConstructor
public class DataDictionaryConstraint implements ConstraintValidator<DataDictionary,String> {

    private DataDicEnum field;

    private final DataDicService dataDicService;

    @Override
    public void initialize(DataDictionary constraintAnnotation) {
        field= constraintAnnotation.field();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("进入到了DataDictionaryConstraint");
        return dataDicService.getOptionsByFiledName(field).contains(s);
    }
}
