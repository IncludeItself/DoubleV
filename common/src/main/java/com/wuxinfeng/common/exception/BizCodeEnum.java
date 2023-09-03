package com.wuxinfeng.common.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/3/2 11:10
 **/

@NoArgsConstructor
@AllArgsConstructor
public enum BizCodeEnum {
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    VALID_EXCEPTION(10001,"参数格式校验异常"),

    USER_PASSWORD_EXCEPTION(11000,"用户名或密码错误"),
    PERMISSION_DENIED_EXCEPTION(11001,"没有权限"),
    CODE_SEND_TOO_OFTEN_EXCEPTION(11002,"验证码发送太频繁"),
    //ExpiredJwtException
    EXPIRED_JWT_EXCEPTION(11003,"Jwt 已过期"),
    JWT_SIGNATURE_EXCEPTION (11004,"Jwt格式错误"),
    JWT_EXPIRED_EXCEPTION(11005,"Jwt已过期"),
    //MalformedJwtException
    JWT_MALFORMED_EXCEPTION(11006,"jwt格式问题，JWT strings must contain exactly 2 period characters. Found: 0"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(11007,"参数不对"),
    PURCHASE_ORDER_MISSING_EXCEPTION(12001,"采购订单不能为空"),
    UNIT_MISSING_EXCEPTION(12002,"物料单位无记录"),
    NO_CURRENT_PERIOD_EXCEPTION(12003,"未打开任何期间")
    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
