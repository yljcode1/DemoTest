package com.yao.aop;

import com.yao.model.entity.Page;
import com.yao.model.protocol.BaseResult;
import com.yao.util.YaoPages;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022/6/21
 * @author: yao
 */
@Aspect
public class WebPageAop {

    @Value("${yao.page.key.num:pageNum}")
    private String pNum;
    @Value("${yao.page.key.size:pageSize}")
    private String pSize;
    @Value("${yao.page.key.total:total}")
    private String pTotal;
    @Value("${yao.page.key.count:count}")
    private String pCount;

    @Around("@within(org.springframework.web.bind.annotation.RestController)||@within(org.springframework.stereotype.Controller)")
    public Object pageHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求参数
        String pageNum = request.getParameter(pNum);
        String pageSize = request.getParameter(pSize);

        // 封装Page对象
        Page page = null;
        if (pageNum != null && pageSize != null) {
            // 缓存到ThreadLocal中
            YaoPages.setPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        }

        // 放行请求
        Object result = null;
        try {
            result = joinPoint.proceed();
            // 获取请求中的Page对象
            page = YaoPages.getPage();
            if (page != null) {
                // 如果有分页信息，则修改返回对象
                if (result instanceof BaseResult) {
                    // 将page分页信息返回到对象上
                    Map<String, Integer> pageMap = new HashMap<>();
                    pageMap.put(pNum, page.getPageNum());
                    pageMap.put(pSize, page.getPageSize());
                    pageMap.put(pTotal, page.getTotal());
                    pageMap.put(pCount, page.getCount());
                    ((BaseResult) result).setPage(pageMap);
                }
                // 业务执行，清空分页缓存
                YaoPages.clearPage();

            }
        } catch (Throwable throwable) {
            // 异常不处理，继续向上抛
            throw throwable;
        }
        return result;
    }
}
