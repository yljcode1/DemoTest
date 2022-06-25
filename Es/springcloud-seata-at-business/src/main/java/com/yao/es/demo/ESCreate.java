//package com.yao.es.demo;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class ESCreate {
//    /**
//     * 创建userIndex
//     *
//     * @throws IOException
//     */
//    @Test
//    public void test01() throws IOException {
//        // 连接es
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        // 创建索引index对象
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
//        // 创建索引
//        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//
////        restHighLevelClient.indices().delete();
////        UpdateRequest updateRequest = new UpdateRequest();
////        updateRequest.index("index").id("1001");
////        updateRequest.doc();
////        restHighLevelClient.update()
//
//
//        // 打印索引
//        System.out.println(createIndexResponse.isAcknowledged());
//    }
//}
