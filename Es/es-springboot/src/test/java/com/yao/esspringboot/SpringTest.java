//package com.yao.esspringboot;
//
//import lombok.RequiredArgsConstructor;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringTest {
//    @Autowired
//    ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Test
//    public void test() {
//
//        final IndexOperations index = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("employee_index"));
//        System.out.println(index);
//    }
//}
