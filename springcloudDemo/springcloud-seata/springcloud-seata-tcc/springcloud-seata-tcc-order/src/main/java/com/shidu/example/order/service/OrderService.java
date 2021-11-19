package com.shidu.example.order.service;

import com.shidu.example.order.model.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
public interface OrderService {
    /**
     * 一阶段方法
     * @param order
     * @return
     */
    @TwoPhaseBusinessAction(name = "createOrder",commitMethod = "commit",rollbackMethod = "rollback")
    Boolean create(BusinessActionContext businessActionContext,
            @BusinessActionContextParameter Order order);

    /**
     * 二阶段提交
     */
    public Boolean commit(BusinessActionContext businessActionContext);

    /**
     * 二阶段回滚
     */
    public Boolean rollback(BusinessActionContext businessActionContext);

}
