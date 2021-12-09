package com.yao.esspringboot.repository;

import com.yao.esspringboot.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployRepository extends ElasticsearchRepository<Employee, String> {
}
