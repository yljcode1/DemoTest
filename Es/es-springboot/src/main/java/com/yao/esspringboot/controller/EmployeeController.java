package com.yao.esspringboot.controller;

import com.yao.esspringboot.model.Employee;
import com.yao.esspringboot.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class EmployeeController {
    public final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    public final ElasticsearchRestTemplate restTemplate;
    public final EmployRepository employRepository;

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    @GetMapping("/index/exists")

    public boolean indexExists(String index) {
        final IndexOperations indexOps = restTemplate.indexOps(IndexCoordinates.of(index));
        final boolean exists = indexOps.exists();
        return exists;
    }

    /**
     * 创建索引
     */
    @GetMapping("/index/create")
    public void indexCreate(String index) {
        //   final IndexOperations indexOps = restTemplate.indexOps(IndexCoordinates.of(index));
        final IndexOperations indexOps = restTemplate.indexOps(Employee.class);
        final boolean b = indexOps.create();
        logger.info(String.valueOf(b));
    }

    /**
     * 删除索引
     */
    @GetMapping("/index/delete")
    public void indexDelete(String index) {
        final String delete = restTemplate.delete(Employee.class);
        System.out.println(delete);
    }

    //数据操作 -doc

    /**
     * 新增数据
     */
    public void save(Employee employee) {
        employRepository.save(employee);
//        restTemplate.save(employee);
    }

    /**
     * 批量新增数据
     */
    public void saveAll(List<Employee> employeeList) {
        employRepository.saveAll(employeeList);
//        restTemplate.save(employeeList);
    }

    /**
     * 修改数据
     */
    @GetMapping("/doc/update")
    public void update(String indexName, Employee employee) throws IOException {
        final UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.retryOnConflict(1);
        updateRequest.doc(employee);
       updateRequest.routing(employee.getId());
        restTemplate.update(UpdateQuery.builder(employee.getId()).build(),IndexCoordinates.of(indexName));

    }

    /**
     * 数据查询
     * @return
     */
    @GetMapping("/get/doc1")
    public List<Object> queryMathList(String field, String value){
        final Criteria criteria = Criteria.where(field).is(value);
        final CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        criteriaQuery.addCriteria(Criteria.where(field).is(value));
        CriteriaQuery.fromQuery(criteriaQuery);
        final SearchHits<Employee> search = restTemplate.search(criteriaQuery, Employee.class);
        final SearchHit<Employee> searchHit = search.getSearchHit(1);
        final List<Object> sortValues = searchHit.getSortValues();
        return sortValues;
    }
}
