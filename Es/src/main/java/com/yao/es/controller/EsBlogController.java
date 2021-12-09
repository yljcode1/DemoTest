package com.yao.es.controller;

import com.yao.es.model.EsBlog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoK
 * @date 2021/12/9
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class EsBlogController {

    public final ElasticsearchRestTemplate template;

    @GetMapping("/get/list")
    public List<EsBlog> elasticSearchTest() {
        // 1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询）,
        /**
         * 组合查询BoolQueryBuilder
         * must(QueryBuilders) :AND
         * mustNot(QueryBuilders) :NoT
         * should:                 :OR
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //builder下面有must，should以及mustNot 相当于sql中的and，or以及not
        //设置模糊查询，博客的简介中有学习两个字
        boolQueryBuilder.must(QueryBuilders.fuzzyQuery("sumary", "学习"));

        //设置要查询博客的标题中含有关键字
        boolQueryBuilder.must(new QueryStringQueryBuilder("man").field("springdemo"));

        //按照博客中的评论数排序
        FieldSortBuilder sort = SortBuilders.fieldSort("commentSize").order(SortOrder.ASC);

        //设置分页
        PageRequest pageRequest = PageRequest.of(0, 10);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withSort(sort);
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        nativeSearchQueryBuilder.withPageable(pageRequest);

        SearchHits<EsBlog> esBlogSearchHits = template.search(nativeSearchQueryBuilder.build(), EsBlog.class);

        List<EsBlog> collect = esBlogSearchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/get/term")
    public List<EsBlog> getTerm() {
        /**
         * 单个匹配termQuery
         */
        //不分词查询，参数1：字段名；参数2：字段查询值，
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("fieldName", "fieldValue");
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("fieldName", "fieldValue");

        /**
         * 多个匹配
         //         */
        //不分词查询，参数1：字段名，参数2：多个字段查询值
        TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("fieldName", "fieldValue");
        //分词查询，采用默认分词器
        QueryBuilders.multiMatchQuery("fieldValue", "fieldName1", "fieldName2", "fieldName3");
        //匹配所有文件相当于就没有查询条件
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(termQueryBuilder);
        SearchHits<EsBlog> search = template.search(nativeSearchQueryBuilder.build(), EsBlog.class);
        List<EsBlog> collect = search.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return collect;
    }

    // 模糊查询
    @GetMapping("/get/mohu")
    public List<EsBlog> getLike() {
        // 常用的字符串查询
        QueryBuilders.queryStringQuery("fieldValue").field("fieldName");
        // 常用的用于推荐相似内容的查询
        QueryBuilders.moreLikeThisQuery(new String[]{"fieldName"});
        // 前缀查询 ，如果字段没分词，就匹配整个字段前缀
        QueryBuilders.prefixQuery("fieldName", "fieldValue");
        // fuzzy query ：分词模糊查询，通过增加fuzziness模糊属性来查询，如果能匹配成功
        QueryBuilders.fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE);
        QueryBuilders.wildcardQuery("fieldName", "ctr*");
        WildcardQueryBuilder fieldName = QueryBuilders.wildcardQuery("fieldName", "c?r?");
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(fieldName);
        return template.search(nativeSearchQueryBuilder.build(), EsBlog.class).stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
