package com.shidu.example.storage.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author jianjun.ren
 * @since 2021/02/16
 */
public interface StorageService {
    /**
     * 一阶段方法
     * @param productId
     * @param used
     * @return
     */
    @TwoPhaseBusinessAction(name = "updateUseNum",commitMethod = "commit",rollbackMethod = "rollback")
    boolean updateUseNum(BusinessActionContext businessActionContext
            , @BusinessActionContextParameter Long productId
            , @BusinessActionContextParameter Long used);

    /**
     * 二阶段提交
     */
    public boolean commit(BusinessActionContext businessActionContext);


    /**
     * 二阶段回滚
     */
    public boolean rollback(BusinessActionContext businessActionContext);
}
