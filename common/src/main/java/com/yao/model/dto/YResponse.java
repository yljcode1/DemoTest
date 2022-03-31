package com.yao.model.dto;

import java.io.Serializable;

/**
 * <p> @description: 统一返回通用类 </p>
 * <p> @date: 2022/3/30 </p>
 *
 * @author: yao
 * @version: 1.0
 */
public class YResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean success;
    private String message;
    private int code;
    private T data;
}
