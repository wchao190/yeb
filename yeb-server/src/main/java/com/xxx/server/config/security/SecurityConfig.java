package com.xxx.server.config.security;

import com.xxx.server.pojo.Admin;
import com.xxx.server.service.impl.EmployeeEcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeEcServiceImpl.IAdminService adminService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    /**
     * 重写登陆认证逻辑
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            //根据用户名查用户信息
            Admin admin = adminService.getAdminByUserName(username);
            if(admin != null){
                return  admin;
            }
            return null;
        };
    }

    /**
     * 构造认证管理器，走自定义登陆认证逻辑
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
    /**
     * 入参配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //jwt 不需要 csrf
        http.csrf().disable();
        //基于 token，不需要session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/login","logout","/captcha")
                .permitAll()
                .anyRequest().authenticated();
        //禁用缓存
        http.headers().cacheControl();
        // 把自己注册的过滤器放在 UsernamePasswordAuthenticationFilter 之前
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //异常处理
        http.exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
    /**
     * 创建密码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}
