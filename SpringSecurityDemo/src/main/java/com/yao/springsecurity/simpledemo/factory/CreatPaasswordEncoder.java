package com.yao.springsecurity.simpledemo.factory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建PasswordEncoder
 *
 * @author xiao.K
 * @date 2021/10/28
 */
public class CreatPaasswordEncoder {
    /**
     * 创建DelegatingPasswordEncoder
     * 本质上方法里面也是创建自定义实例
     */
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    /**
     * 自定义创建实例
     */
    public static PasswordEncoder createDelegating(){
        String idForEncode = "bcrypt";
        Map<String,PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode,new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode,encoders);
    }
}
