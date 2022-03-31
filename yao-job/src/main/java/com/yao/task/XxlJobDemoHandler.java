package com.yao.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * @author xiaoK
 * @date 2022/3/31
 */
@Component
public class XxlJobDemoHandler {
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("java,hello world");
        XxlJobLogger.log("param:{}", param);
        return ReturnT.SUCCESS;
    }
}
