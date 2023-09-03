package com.wuxinfeng.common.jwt;

/**
 * @BelongsProject: DoubleV
 * @BelongsPackage: com.wuxinfeng.common.jwt
 * @Author: yanhongwei
 * @CreateTime: 2023-03-21  10:01
 * @Description: TODO
 * @Version: 1.0
 */
public class JwtConstant {
    public static final String secretKey="7d78fdddddddddd34reww3e33333e3dddddddfkskeeeeeeeeew";
    public static final long expired= 144000000L;  //1440000L因为测试改成了100倍。

    public static final long refreshExpired= 144000L;
    public static final String tokenHeader="authorization";
    public static final String tokenHead="Bearer ";
    public static final String JWT_USERNAME ="username";
    public static final String JWT_ROLES="roles";
    public static final String PLANT_SCOPES="plantScopes";



}
