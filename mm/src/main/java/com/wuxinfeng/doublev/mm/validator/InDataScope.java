package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.ScopeFieldEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-26  20:53
 */
@Documented
@Constraint(validatedBy = {InDataScopeConstraint.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InDataScope {
    String message() default "权限范围内值无效";
    ScopeFieldEnum field() default ScopeFieldEnum.PLANT;
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};
}
