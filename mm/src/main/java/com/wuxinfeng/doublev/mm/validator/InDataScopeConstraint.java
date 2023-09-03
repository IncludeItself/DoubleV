package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.ScopeFieldEnum;
import com.wuxinfeng.doublev.mm.interceptor.MMInterceptor;
import com.wuxinfeng.doublev.mm.service.CurrencyRateService;
import com.wuxinfeng.doublev.mm.service.MaterialsBasicService;
import com.wuxinfeng.doublev.mm.service.MovementsConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-26  20:55
 */
@RequiredArgsConstructor
public class InDataScopeConstraint implements ConstraintValidator<InDataScope,String> {

    private ScopeFieldEnum field;
    private final MovementsConfigService movementsConfigService;
    private final MaterialsBasicService materialsBasicService;
    private final CurrencyRateService currencyRateService;


    @Override
    public void initialize(InDataScope constraintAnnotation) {
        field= constraintAnnotation.field();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (field== ScopeFieldEnum.PLANT) {
            for (String plantScope : MMInterceptor.threadLocal.get().getPlantScopes()) {
                if (plantScope.equalsIgnoreCase(s)) return true;
                else continue;
            }
        }
        if (field== ScopeFieldEnum.MATERIAL_CODES) {
            return materialsBasicService.materialCodeExist(s);
        }
        if (field== ScopeFieldEnum.CURRENCIES) {
            return currencyRateService.currencyExist(s);
        }
        return false;
    }
}
