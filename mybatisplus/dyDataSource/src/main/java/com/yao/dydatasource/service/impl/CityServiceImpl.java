package com.yao.dydatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yao.dydatasource.entity.City;
import com.yao.dydatasource.mapper.CityMapper;
import com.yao.dydatasource.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiaoK
 * @date 2021/12/10
 */
@Service
@RequiredArgsConstructor
@DS("slave_1")
public class CityServiceImpl implements CityService {
    public final CityMapper mapper;

    @Override
    public void insertCity(final City city) {
        mapper.insert(city);
    }
}
