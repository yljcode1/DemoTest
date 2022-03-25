//package com.yao.esjava;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//
//import java.io.IOException;
//
///**
// * Java-Es
// */
//@Slf4j
//public class EsTest_Client {
//    public static void main(String[] args) throws IOException {
//        // 创建ES客户端
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http"))
//        );
//    }
//}
