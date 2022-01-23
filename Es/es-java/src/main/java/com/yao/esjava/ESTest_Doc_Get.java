package com.yao.esjava;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;

import java.io.IOException;
import java.util.Map;

public class ESTest_Doc_Get {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 查询数据
        GetRequest request = new GetRequest();
        request.index("user").id("1001");
        GetResponse doc = esClient.get(request, RequestOptions.DEFAULT);
        String source = doc.getSourceAsString();
        System.out.println(source);
        esClient.close();
    }
}
