package com.wuxinfeng.doublev.gateway.controller;

import com.wuxinfeng.common.exception.BizCodeEnum;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.R;
import com.wuxinfeng.doublev.gateway.constant.RedisConstant;
import com.wuxinfeng.doublev.gateway.entity.UserInfoEntity;
import com.wuxinfeng.doublev.gateway.feign.ThirdPartyFeignService;
import com.wuxinfeng.doublev.gateway.service.UserInfoService;
import com.wuxinfeng.doublev.gateway.vo.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;



/**
 * 
 *
 * @author Wu Xinfeng
 * @email sunlightcs@gmail.com
 * @date 2023-02-18 13:23:43
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("gateway/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    private final ThirdPartyFeignService thirdPartyFeignService;

    private final StringRedisTemplate stringRedisTemplate;


    /**
     * @Description 发送手机验证短信并保存在redis中
     * @Author Wu Xinfeng
     * @Date 2023/3/3 14:54
     **/
    @PostMapping("/sendcode")
    //   @RequiresPermissions("gateway:userinfo:list")
    public R sendSms(@RequestParam String phoneNumber){
        String redisCode = stringRedisTemplate.opsForValue().get(RedisConstant.REDIS_LONGIN_PRE + phoneNumber);
        if(redisCode!=null&&System.currentTimeMillis()-Long.parseLong(redisCode.split("_")[1])<60*1000){
            return R.error(BizCodeEnum.CODE_SEND_TOO_OFTEN_EXCEPTION.getCode(),BizCodeEnum.CODE_SEND_TOO_OFTEN_EXCEPTION.getMsg());
        }
        String code = (int)((Math.random() * 9 + 1) * 100000)+"";
        System.out.println("这里是sendcode接口，生成验证码："+code);
        stringRedisTemplate.opsForValue().set(RedisConstant.REDIS_LONGIN_PRE+phoneNumber,code+"_"+System.currentTimeMillis(),5, TimeUnit.MINUTES);
        CompletableFuture<R> rCompletableFuture = CompletableFuture.supplyAsync(() -> thirdPartyFeignService.sendCode(phoneNumber, code));
//        thirdPartyFeignService.sendCode(phoneNumber, code);
        return R.ok();
    }


    /**
     * @Description 注册，成功后直接返回jwt
     * @Author Wu Xinfeng
     * @Date 2023/2/24 13:57
     **/
    @PostMapping("/register")
    //   @RequiresPermissions("gateway:userinfo:list")
    public R register(RegisterRequest request){
        return R.ok().put("data",userInfoService.register(request));
    }



    /**
     * 列表
     */
    @RequestMapping("/list")
 //   @RequiresPermissions("gateway:userinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
  //  @RequiresPermissions("gateway:userinfo:info")
    public R info(@PathVariable("id") Integer id){
		UserInfoEntity userInfo = userInfoService.getById(id);

        return R.ok().put("userInfo", userInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
  //  @RequiresPermissions("gateway:userinfo:save")
    public R save(@RequestBody UserInfoEntity userInfo){
		userInfoService.save(userInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("gateway:userinfo:update")
    public R update(@RequestBody UserInfoEntity userInfo){
		userInfoService.updateById(userInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("gateway:userinfo:delete")
    public R delete(@RequestBody Integer[] ids){
		userInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
