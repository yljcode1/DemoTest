package com.yao.springsecurity.simpledemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author xiao.K
 * @date 2021/10/29
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 提供一个加密Encoder实例
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 新增SecurityConfig
     *
     * @param auth builder
     * @throws Exception 抛出异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存中定义用户
//        auth.inMemoryAuthentication()
//                .withUser("javaboy.org")
//                .password("123456")
//                .roles("admin")
//                .and()
//                .withUser("zhangsan")
//                .password("wed")
//                .roles("admin")
//                .authorities("/");
        auth.inMemoryAuthentication().withUser("vip1").password("vip").roles("vip1")
                .and()
                .withUser("vip2").password("vip").roles("vip2");
    }

    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("javaboy").password("123").roles("admin").build());
        return inMemoryUserDetailsManager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/security/vip1").hasRole("vip1")
                .antMatchers("/security/vip2").hasRole("vip2")
                .antMatchers("/security/vip3").hasRole("vip3")
                .anyRequest().permitAll();
    }
}
