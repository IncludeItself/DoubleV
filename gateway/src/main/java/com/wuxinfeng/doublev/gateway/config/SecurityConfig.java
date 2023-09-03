package com.wuxinfeng.doublev.gateway.config;


import com.wuxinfeng.doublev.gateway.config.handler.MyAccessDeniedHandler;
import com.wuxinfeng.doublev.gateway.config.handler.MyAuthenticationFailureHandler;
import com.wuxinfeng.doublev.gateway.config.handler.MyAuthenticationSuccessHandler;
import com.wuxinfeng.doublev.gateway.config.jwt.JwtAuthenticationFilter;
import com.wuxinfeng.doublev.gateway.config.jwt.JwtSecurityContextRepository;
import com.wuxinfeng.doublev.gateway.config.smsAuthentication.SmsAuthenticationFilter;
import com.wuxinfeng.doublev.gateway.config.smsAuthentication.SmsAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/22 17:05
 **/


@EnableWebFluxSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private String URL_WHITELIST[] = {
            "/",
            "/login.html",
            "/error.html",
            "/toError",
            "/showLogin",
            "/js/**",
            "/css/**",
            "/image/**",
            "/login",
            "/sms/login",
            "/gateway/userinfo/sendcode",
            "/gateway/userinfo/register"
//            "/test/message",
    };

    private final MyAuthorizationManager myAuthorizationManager;
    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final MyAccessDeniedHandler myAccessDeniedHandler;
    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SmsAuthenticationManager smsAuthenticationManager;

    @Bean
    @Primary
    public UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepositoryReactiveAuthenticationManager(){
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }
    //关闭session
    @Bean
    public WebSessionManager webSessionManager(){
        return exchange -> Mono.empty();
    }

    @Bean
    public SmsAuthenticationFilter smsAuthenticationFilter(){
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter(smsAuthenticationManager);
        smsAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/smslogin"));
        smsAuthenticationFilter.setServerAuthenticationConverter(new ServerFormLoginAuthenticationConverter());
        smsAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return smsAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtSecurityContextRepository jwtSecurityContextRepository(){
        return JwtSecurityContextRepository.getInstance();
    }


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        //取消session，这种方法浏览器cookie还是有session,只是不起作用
//        httpSecurity.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());

        httpSecurity.authorizeExchange()
                .pathMatchers(URL_WHITELIST).permitAll()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/**").access(myAuthorizationManager)
                .anyExchange().authenticated();

       httpSecurity.formLogin()
               .authenticationManager(userDetailsRepositoryReactiveAuthenticationManager())
               .authenticationSuccessHandler(myAuthenticationSuccessHandler)
                .authenticationFailureHandler(myAuthenticationFailureHandler)
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), SecurityWebFiltersOrder.REACTOR_CONTEXT);

       httpSecurity
               .addFilterBefore(smsAuthenticationFilter(), SecurityWebFiltersOrder.FORM_LOGIN)
//               .authenticationManager(smsAuthenticationManager)
               .securityContextRepository(jwtSecurityContextRepository())
       ;



        return httpSecurity.build();
    }
}
