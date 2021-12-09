package com.yao.es.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;

import java.io.IOException;

public class ESFindUpdate {
    @Test
    public void test01() throws IOException {
        final RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        final GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        final GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("user").id();
//        indexRequest.index()
        indexRequest.source();
    }
}
