package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.doublev.mm.entity.MaterialVouchersEntity;
import com.wuxinfeng.doublev.mm.exception.PlantOverRangeException;
import com.wuxinfeng.doublev.mm.interceptor.MMInterceptor;
import com.wuxinfeng.doublev.mm.service.PlantInfoService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-07-15  23:47
 */
@RequiredArgsConstructor
public class PeriodAllowedConstraint implements ConstraintValidator<PeriodAllowed, MaterialVouchersEntity> {
    private final PlantInfoService plantInfoService;

    @Override
    public void initialize(PeriodAllowed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MaterialVouchersEntity materialVouchers, ConstraintValidatorContext constraintValidatorContext) {
        System.out.printf("进入了PeriodAllowedConstraint的isValid方法");
        return true;
//        String plant = materialVouchers.getPlant();
//        if (!MMInterceptor.threadLocal.get().getPlantScopes().contains(plant)) {
//            throw new PlantOverRangeException();
//        }
//        return plantInfoService.isPeriodAllowed(plant,materialVouchers.getPostDate());
    }
}
