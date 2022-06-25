package com.yao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2022/4/26
 * @author: yao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 考试分数
     */
    private String test;
}
