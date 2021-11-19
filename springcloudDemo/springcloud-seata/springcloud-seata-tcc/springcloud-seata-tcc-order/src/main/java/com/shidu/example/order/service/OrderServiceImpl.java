package com.shidu.example.order.service;

import com.shidu.example.order.mapper.OrderMapper;
import com.shidu.example.order.model.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author xiaoK
 * @date 2021/11/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final TransactionTemplate transactionTemplate;

    /**
     * 一阶段准备，冻结
     * @param order 订单
     * @return 是否成功
     */
    @Override
    @Transactional
    public Boolean create(BusinessActionContext businessActionContext,Order order) {
//        log.info("创建订单开始");
//        int index = orderMapper.insert(order);
//        log.info("创建订单结束");
//        return index > 0;
        final String xid = businessActionContext.getXid();
        return transactionTemplate.execute((status)->{
            try{
                //预留资源
                return true;
            }catch (Throwable throwable){
                throwable.printStackTrace();
                status.setRollbackOnly();
                return false;
            }
        });
    }

    @Override
    public Boolean commit(BusinessActionContext businessActionContext) {
        final String xid = businessActionContext.getXid();
        Order order = (Order) businessActionContext.getActionContext("order");
        orderMapper.insert(order);
        return true;
    }

    @Override
    public Boolean rollback(BusinessActionContext businessActionContext) {
        return true;
    }
}
