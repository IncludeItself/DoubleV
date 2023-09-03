package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.ScopeFieldEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-08-29  13:15
 */
@Documented
@Constraint(validatedBy = {NotZeroConstraint.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotZero {

    String message() default "不能等于0";
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
}
