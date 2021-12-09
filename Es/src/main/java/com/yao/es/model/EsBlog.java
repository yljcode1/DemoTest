package com.yao.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author xiaoK
 * @date 2021/12/9
 */
@Data
@Document(indexName = "blog", createIndex = false)
public class EsBlog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Field(index = false)
    private Long blogId;
    private String title;
    private String summary;
    private String content;
    private String tags;
}
