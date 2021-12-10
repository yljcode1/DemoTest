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
@TableName("t_city")
public class City {
    private String id;
    private String cityName;
    private String address;
}
