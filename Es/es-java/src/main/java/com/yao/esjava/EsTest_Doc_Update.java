package com.yao.esjava;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsTest_Doc_Update {
    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 插入数据
        // 向ES插入数据，必须数据转换为JSON格式
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        String jsonString = JSON.toJSONString(User.builder().age(12).name("zhangsan").sex("男").build());
        request.doc(jsonString, XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = response.getResult();
        restHighLevelClient.close();
    }
}
