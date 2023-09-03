package com.wuxinfeng.doublev.mm.datapermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: TODO
 * @Author: Wu Xinfeng
 * @CreateTime: 2023-05-25  22:23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDataPermission {
//    PermissionFieldEnum field();
//    PermissionFieldEnum vendorfield();
//    BillTypeEnum billType();
//    OperationTypeEnum operation();
}
