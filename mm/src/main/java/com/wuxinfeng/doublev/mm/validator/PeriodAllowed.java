package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.ScopeFieldEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-07-15  23:44
 */
@Documented
@Constraint(validatedBy = {PeriodAllowedConstraint.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodAllowed {
    String message() default "期间没有打开";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
}
