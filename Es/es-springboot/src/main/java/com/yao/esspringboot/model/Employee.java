package com.yao.esspringboot.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName = "employee_index_4",shards = 1,replicas = 0, createIndex = false)
@Setting
public class Employee {
    @Id
    private String id;
    /**
     * 员工编码
     */
    @Field(type = FieldType.Keyword,name = "student_code")
    private String studentCode;

    @Field(type = FieldType.Keyword,name = "name")
    private String name;

    /**
     * 员工简历
     */
    @Field(type = FieldType.Text)
    private String desc;

    /**
     * 员工住址
     */
    @Field(type = FieldType.Text)
    private Integer type;

    /**
     * 手机号码
     */
    @Field(type = FieldType.Keyword)
    private String mobile;

}
