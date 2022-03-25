//package com.yao.esjava;
//
//import com.alibaba.fastjson.JSON;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.DocWriteResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//
//import java.io.IOException;
//
///**
// * 文档的创建
// */
//public class EsTest_Doc_Insert {
//    public static void main(String[] args) throws IOException {
//        // 创建客户端
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http"))
//        );
//        // 插入数据
//        IndexRequest request = new IndexRequest();
//        request.index("user").id("1001");
//        // 向ES插入数据，必须数据转换为JSON格式
//        String jsonString = JSON.toJSONString(User.builder().age(12).name("zhangsan").sex("男").build());
//        request.source(jsonString, XContentType.JSON);
//        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
//        DocWriteResponse.Result result = response.getResult();
//        restHighLevelClient.close();
//    }
//}
