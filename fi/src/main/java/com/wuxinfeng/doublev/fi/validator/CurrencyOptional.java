package com.wuxinfeng.doublev.fi.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-03-29  09:39
 */
@Documented
@Constraint(validatedBy = {CurrencyOptionalConstraint.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyOptional {
    String message() default "不在可选范围内，吴鑫峰说";

    Class<?>[] groups() default {};

    Class<? extends javax.validation.Payload>[] payload() default {};
}
