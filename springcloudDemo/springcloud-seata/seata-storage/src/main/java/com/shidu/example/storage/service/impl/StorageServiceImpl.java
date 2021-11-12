package com.shidu.example.storage.service.impl;

import com.shidu.example.storage.mapper.StorageMapper;
import com.shidu.example.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {


    private final StorageMapper storageMapper;

    @Override
    @Transactional
    public boolean updateUseNum(long productId , long used) {
//        int a = 100/0;
        int index = storageMapper.updateUsed(productId, used);
        return index > 0;
    }
}
