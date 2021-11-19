package com.shidu.example.storage.service.impl;

import com.shidu.example.storage.mapper.StorageMapper;
import com.shidu.example.storage.model.Storage;
import com.shidu.example.storage.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    //减库存
    private final StorageMapper storageMapper;

    private final TransactionTemplate transactionTemplate;

    /**
     * 第一阶段检查，准备
     *
     * @param productId 产品id
     * @param used      买多少
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean updateUseNum(BusinessActionContext businessActionContext, Long productId, Long used) {
//        int a = 100/0;
//        int index = storageMapper.updateUsed(productId, used);
//        return index > 0;
        //分布式事务ID
        String xid = businessActionContext.getXid();
        //检查是否有该订单
        //检查订单是否足够
        //待减库存作为不可用库存
        return Boolean.TRUE.equals(transactionTemplate.execute((status) -> {
            try {
                Storage storage = new Storage();
                storage.setProductId(productId);
                //检查是否有该订单
                List<Storage> storages = storageMapper.selectByExample(storage);
                if (CollectionUtils.isEmpty(storages)) {
                    System.out.println("该订单不存在");
                    return false;
                }
                //检查订单是否足够
                if (storages.get(0).getTotal() - used < 0) {
                    System.out.println("库存不足，xid=" + xid);
                    return false;
                }
                //待减库存作为不可用库存
                long freezedStorage = storage.getFreezedStorage() + used;
                storage.setFreezedStorage(freezedStorage);
                storageMapper.updateByPrimaryKeySelective(storage);
                return true;
            } catch (Throwable t) {
                t.printStackTrace();
                status.setRollbackOnly();
                return false;
            }
        }));
    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        //分布式事务ID
        final String xid = businessActionContext.getXid();
        //订单ID
        long productId = Long.parseLong((String) businessActionContext.getActionContext("productId"));
        long used = Long.parseLong((String) businessActionContext.getActionContext("used"));

        return Boolean.TRUE.equals(transactionTemplate.execute((transactionStatus -> {
            try {
                Storage storage = new Storage();
                storage.setProductId(productId);
                List<Storage> storages = storageMapper.selectByExample(storage);
                //减库存
                final Long newStorage = storage.getTotal() - used;
                storage.setTotal(newStorage);

                //冻结库存 清除
                storage.setFreezedStorage(storage.getFreezedStorage() + used);
                storageMapper.updateByPrimaryKeySelective(storage);
                System.out.println("xid=" + xid);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                transactionStatus.setRollbackOnly();
                return false;
            }
        })));
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        //分布式事务ID
        final String xid = businessActionContext.getXid();
        //订单ID
        long productId = Long.parseLong((String) businessActionContext.getActionContext("productId"));
        long used = Long.parseLong((String) businessActionContext.getActionContext("used"));

        return Boolean.TRUE.equals(transactionTemplate.execute((transactionStatus -> {
            try {
                Storage storage = new Storage();
                storage.setProductId(productId);
                List<Storage> storages = storageMapper.selectByExample(storage);
                if (null ==storages.get(0)){
                    //订单库存不存在，直接回滚
                    return true;
                }
                //冻结库存 清除
                storage.setFreezedStorage(storage.getFreezedStorage() + used);
                storageMapper.updateByPrimaryKeySelective(storage);
                System.out.println("xid=" + xid);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                transactionStatus.setRollbackOnly();
                return false;
            }
        })));
    }


}
