package com.yao.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;

/**
 * @author xiaoK
 * @date 2021/12/9
 */
@Configuration
public class EsConfig extends ElasticsearchConfigurationSupport {

    /**
     * 配置驼峰mapping
     *
     * @return 返回命名规则
     */
    @Override
    protected FieldNamingStrategy fieldNamingStrategy() {
        return new SnakeCaseFieldNamingStrategy();
    }
}
