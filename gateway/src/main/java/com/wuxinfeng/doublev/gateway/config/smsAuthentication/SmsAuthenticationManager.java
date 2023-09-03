package com.wuxinfeng.doublev.gateway.config.smsAuthentication;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wuxinfeng.doublev.gateway.constant.RedisConstant;
import com.wuxinfeng.doublev.gateway.service.UserInfoService;
import com.wuxinfeng.doublev.gateway.service.impl.UserInfoServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/3/3 10:27
 **/
@Data
@Component
@RequiredArgsConstructor
public class SmsAuthenticationManager extends AbstractUserDetailsReactiveAuthenticationManager {

    private final StringRedisTemplate stringRedisTemplate;
    private final UserInfoService userInfoService;
    protected Log logger = LogFactory.getLog(getClass());
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private Scheduler scheduler = Schedulers.boundedElastic();
    private UserDetailsChecker preAuthenticationChecks = this::defaultPreAuthenticationChecks;
    private UserDetailsChecker postAuthenticationChecks = this::defaultPostAuthenticationChecks;

    private void defaultPreAuthenticationChecks(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            this.logger.debug("User account is locked");
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
                    "User account is locked"));
        }
        if (!user.isEnabled()) {
            this.logger.debug("User account is disabled");
            throw new DisabledException(
                    this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        }
        if (!user.isAccountNonExpired()) {
            this.logger.debug("User account is expired");
            throw new AccountExpiredException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }
    }

    private void defaultPostAuthenticationChecks(UserDetails user) {
        if (!user.isCredentialsNonExpired()) {
            this.logger.debug("User account credentials have expired");
            throw new CredentialsExpiredException(this.messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
        }
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        System.out.println("调用SmsAuthenticationManager的authenticate了！！！");
        String mobilePhone = authentication.getName();
        String presentedPassword = (String) authentication.getCredentials();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        System.out.println("Credentials: "+presentedPassword);
        System.out.println("codeGot: "+ops.get(RedisConstant.REDIS_LONGIN_PRE+mobilePhone));
        // @formatter:off
        return retrieveUser(mobilePhone)
                .doOnNext(this.preAuthenticationChecks::check)
                .publishOn(this.scheduler)
                .filter((userDetails) -> {
                    String redisCode =ops.get(RedisConstant.REDIS_LONGIN_PRE+mobilePhone);
                    if (StringUtils.isEmpty(redisCode)) {
                        throw new BadCredentialsException("验证码已经过期或尚未发送，请重新发送验证码");
                    }
                    String code = redisCode.split("_")[0];
                    if (!presentedPassword.equals(code)) {
                        throw new BadCredentialsException("输入的验证码不正确，请重新输入");
                    }
                    stringRedisTemplate.delete(RedisConstant.REDIS_LONGIN_PRE+mobilePhone);
                    return true;
                })
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid smsCode"))))
//                .flatMap((userDetails) -> upgradeEncodingIfNecessary(userDetails, presentedPassword))
                .doOnNext(this.postAuthenticationChecks::check)
                .map(c->{
                    System.out.println("userDetail: "+c);
                        return this.createUsernamePasswordAuthenticationToken(c);
                });
        // @formatter:on
    }

//    private Mono<UserDetails> upgradeEncodingIfNecessary(UserDetails userDetails, String presentedPassword) {
//        boolean upgradeEncoding = this.userDetailsPasswordService != null
//                && this.passwordEncoder.upgradeEncoding(userDetails.getPassword());
//        if (upgradeEncoding) {
//            String newPassword = this.passwordEncoder.encode(presentedPassword);
//            return this.userDetailsPasswordService.updatePassword(userDetails, newPassword);
//        }
//        return Mono.just(userDetails);
//    }

    private UsernamePasswordAuthenticationToken createUsernamePasswordAuthenticationToken(UserDetails userDetails) {
        return UsernamePasswordAuthenticationToken.authenticated(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    /**
     * The {@link PasswordEncoder} that is used for validating the password. The default
     * is {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     *
     * @param passwordEncoder the {@link PasswordEncoder} to use. Cannot be null
     */
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
//        this.passwordEncoder = passwordEncoder;
//    }

    /**
     * Sets the {@link Scheduler} used by the
     * {@link UserDetailsRepositoryReactiveAuthenticationManager}. The default is
     * {@code Schedulers.newParallel(String)} because modern password encoding is a CPU
     * intensive task that is non blocking. This means validation is bounded by the number
     * of CPUs. Some applications may want to customize the {@link Scheduler}. For
     * example, if users are stuck using the insecure
     * {@link org.springframework.security.crypto.password.NoOpPasswordEncoder} they might
     * want to leverage {@code Schedulers.immediate()}.
     *
     * @param scheduler the {@link Scheduler} to use. Cannot be null.
     * @since 5.0.6
     */
    public void setScheduler(Scheduler scheduler) {
        Assert.notNull(scheduler, "scheduler cannot be null");
        this.scheduler = scheduler;
    }

    /**
     * Sets the service to use for upgrading passwords on successful authentication.
     *
     * @param userDetailsPasswordService the service to use
     */
//    public void setUserDetailsPasswordService(ReactiveUserDetailsPasswordService userDetailsPasswordService) {
//        this.userDetailsPasswordService = userDetailsPasswordService;
//    }

    /**
     * Sets the strategy which will be used to validate the loaded <tt>UserDetails</tt>
     * object after authentication occurs.
     *
     * @param postAuthenticationChecks The {@link UserDetailsChecker}
     * @since 5.2
     */
    public void setPostAuthenticationChecks(UserDetailsChecker postAuthenticationChecks) {
        Assert.notNull(this.postAuthenticationChecks, "postAuthenticationChecks cannot be null");
        this.postAuthenticationChecks = postAuthenticationChecks;
    }

    /**
     * @since 5.5
     */
    @Override
    public void setMessageSource(MessageSource messageSource) {
        Assert.notNull(messageSource, "messageSource cannot be null");
        this.messages = new MessageSourceAccessor(messageSource);
    }

    /**
     * Allows subclasses to retrieve the <code>UserDetails</code> from an
     * implementation-specific location.
     *
     * @param username The username to retrieve
     * @return the user information. If authentication fails, a Mono error is returned.
     */
    protected Mono<UserDetails> retrieveUser(String username){
        return userInfoService.findByMobilePhone(username);
    }
}
