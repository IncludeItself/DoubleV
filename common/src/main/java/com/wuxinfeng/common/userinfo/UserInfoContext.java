package com.wuxinfeng.common.userinfo;

/**
 * @BelongsProject: DoubleV
 * @BelongsPackage: com.wuxinfeng.common.userinfo
 * @Author: yanhongwei
 * @CreateTime: 2023-03-21  09:37
 * @Description: TODO
 * @Version: 1.0
 */
public class UserInfoContext {
    private static ThreadLocal<UserInfo> userInfo = new ThreadLocal<>();

    public static UserInfo getUser() {
        return (UserInfo) userInfo.get();
    }

    public static void setUser(UserInfo user) {
        userInfo.set(user);
    }

    public static void remove(){
        userInfo.remove();
    }

}
