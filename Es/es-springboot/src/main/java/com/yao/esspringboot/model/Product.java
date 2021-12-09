package com.yao.esspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "shopping",shards = 3)
public class Product {
    @Id
    private Long id;
    /**
     * type:字段数据类型
     * analyzer:分词器类型
     * index:是否索引
     * keyword:短语，不进行分词
     *
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Keyword,index = false)
    private String images;
}
