package com.yao.dydatasource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author xiaoK
 * @date 2021/12/10
 */
@Data
@Builder
@TableName("t_student")
public class Student {
    private String id;
    private String userName;
    private Integer age;
}
