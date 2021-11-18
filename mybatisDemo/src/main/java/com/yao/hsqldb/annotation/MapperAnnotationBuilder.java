package com.yao.hsqldb.annotation;

import org.apache.ibatis.session.Configuration;


import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaoK
 * @date 2021/11/18
 */
public class MapperAnnotationBuilder {
    private static final Set<Class<? extends Annotation>> statementAnnotationTypes = Stream.of(Add.class, Desc.class).collect(Collectors.toSet());
    private final Configuration configuration;
    private final Class<?> type;


    public MapperAnnotationBuilder(Configuration configuration, Class<?> type) {
        this.configuration = configuration;
        this.type = type;
    }
}
