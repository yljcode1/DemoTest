package com.yao.springsecurity.simpledemo.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xiao.K
 * @date 2021/10/28
 */
public class UserTest {
    @Test
    public void test01(){
        // 创建单个用户
        User user = (User) User.withDefaultPasswordEncoder().username("user")
                .password("password").roles("user")
                .build();
        System.out.println(user.getPassword());
        String now = LocalDateTime.now().toString();
        String localdate = LocalDate.now().toString();

        // 创建多个用户
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetails userDetails = userBuilder.username("user").password("password").roles("USER").build();
        UserDetails admin = userBuilder.username("admin").password("password").roles("USER", "ADMIN").build();

        // BCryptPasswordEncoder 使用bcrypt算法对密码进行散列
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        Assert.assertTrue(encoder.matches("myPassword", result));

        //Argon2PasswordEncoder 使用argon2算法对密码进行散列
        Argon2PasswordEncoder encoder1 =  new Argon2PasswordEncoder();
        String result2 = encoder1.encode("myPassword");
        Assert.assertTrue(encoder1.matches("myPassword",result2));


    }
}
