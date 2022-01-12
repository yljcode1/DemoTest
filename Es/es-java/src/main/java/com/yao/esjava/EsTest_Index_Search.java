package com.yao.esjava;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.common.settings.Settings;

import javax.xml.ws.Holder;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 索引的查找
 */
public class EsTest_Index_Search {
    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 查找索引
        GetIndexRequest getIndexRequest = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        Map<String, List<AliasMetadata>> aliases = getIndexResponse.getAliases();
        String[] indices = getIndexResponse.getIndices();
        Map<String, Settings> defaultSettings = getIndexResponse.getDefaultSettings();
    }
}
