package com.yao.esspringboot.repository;

import com.yao.esspringboot.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDao extends ElasticsearchRepository<Product,Long>{
}
