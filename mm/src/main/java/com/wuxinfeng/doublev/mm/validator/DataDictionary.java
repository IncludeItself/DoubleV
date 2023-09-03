package com.wuxinfeng.doublev.mm.validator;

import com.wuxinfeng.common.enums.DataDicEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-09-02  10:59
 */
@Documented
@Constraint(validatedBy = {DataDictionaryConstraint.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataDictionary {

    String message() default "数据字典中此字段无此选项";
    DataDicEnum field() default DataDicEnum.FIELD;
    Class<?>[] groups() default {};
    Class<? extends javax.validation.Payload>[] payload() default {};


}
