package com.yao.hsqldb;

import com.yao.hsqldb.annotation.Add;
import com.yao.hsqldb.annotation.Desc;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.lang.annotation.*;

/**
 * @author xiaoK
 * @date 2021/11/18
 */
public interface Example03 {
    @Add(add = "tianjia yige shuzi nicai shishengme")
    public void selectAdd(String a);

    @Desc(desc = "jianshao yige shuzi nicai shishengme")
    public void selectDesc(String b);
}

