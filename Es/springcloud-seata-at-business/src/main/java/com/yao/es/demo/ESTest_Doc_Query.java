//package com.yao.es.demo;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//
//import java.io.IOException;
//
//public class ESTest_Doc_Query {
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost",9200,"http"))
//        );
//        final SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("user");
//        searchRequest.source(new SearchSourceBuilder().query(
//                QueryBuilders.matchAllQuery()
//        ));
//        esClient.search(searchRequest, RequestOptions.DEFAULT);
//    }
//}
