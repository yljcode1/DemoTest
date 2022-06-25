package com.yao.util;

import com.yao.model.entity.Page;

import java.awt.print.Pageable;

/**
 * Page对象缓存的ThreadLocal工具类
 *
 * @date: 2022/6/21
 * @author: yao
 */
public class YaoPages {
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Boolean> autoThreadLocal = new ThreadLocal<>();

    /**
     * 分页相关的方法
     *
     * @return Page对象
     */
    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public static void setPage(Integer pageNum, Integer pageSize, boolean isBatch) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setEnable(!isBatch);
        YaoPages.pageThreadLocal.set(page);
    }

    public static void setPage(Integer pageNum, Integer pageSize) {
        YaoPages.setPage(pageNum, pageSize, false);
    }

    public static void clearPage() {
        YaoPages.pageThreadLocal.set(null);
    }

    /*
    结果集自动映射相关的方法
     */
    public static Boolean getAutoFlag() {
        return autoThreadLocal.get();
    }

    public static void setAutoFlag(Boolean autoFlag) {
        autoThreadLocal.set(autoFlag);
    }
}
