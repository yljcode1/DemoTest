package com.yao.model.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一返回接口对象
 *
 * @date: 2022/6/22
 * @author: yao
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult implements Serializable {
    /**
     * 分页对象
     */
    private Map<String, Integer> page;

    /**
     * 无参构造方法
     */
    public BaseResult() {
    }

    public Map<String, Integer> getPage() {
        return page;
    }

    public void setPage(Map<String, Integer> page) {
        this.page = page;
    }
}
