package com.yao.esjava;

import org.apache.http.HttpHost;
import org.bouncycastle.cert.ocsp.Req;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * 高级查询
 */
public class ESTest_Doc_Query {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        // 查询数据
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchRequest source = request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse search = esClient.search(source, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(search.getTook());
    }

}
