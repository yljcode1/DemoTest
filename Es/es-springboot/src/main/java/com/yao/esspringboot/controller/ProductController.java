package com.yao.esspringboot.controller;

import com.yao.esspringboot.model.Product;
import com.yao.esspringboot.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    public final ElasticsearchRestTemplate restTemplate;
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    public final ProductDao productDao;

    @GetMapping("/create/index")
    public void createIndex() {
        final IndexOperations indexOperations = restTemplate.indexOps(Product.class);
        final boolean exists = indexOperations.exists();
        logger.info(String.valueOf(exists));
    }

    @GetMapping("/delete/index")
    public void deleteIndex() {
        final String delete = restTemplate.delete(Product.class);
        logger.info(delete);
    }
    //文档操作
    @PutMapping("/inset/doc")
    public void insertDoc(){
        final Product product = new Product();
        product.setId(2L);
        product.setTitle("三星手机");
        product.setCategory("手机");
        product.setPrice(2333.0);
        product.setImages("http://www.atguigu/sx.jpg");
        productDao.save(product);
    }
    //修改
    @PutMapping("/update/doc")
    public void updaetDoc(){
        final Product product = new Product();
        product.setId(2L);
        product.setTitle("三星手机");
        product.setCategory("手机");
        product.setPrice(4999.0);
        product.setImages("http://www.atguigu.sx.jpg");
        final Product save = productDao.save(product);
    }

    //根据id查询
    @GetMapping("/get/doc")
    public void getDoc(){
        final Product product = productDao.findById(1L).get();
        System.out.println(product);
    }
    // 获取所有的文档
    @GetMapping("/get/all")
    public void getAllDoc(){
        final Iterable<Product> all = productDao.findAll();
        for (Product product : all) {
            System.out.println(product);

        }
    }
    @DeleteMapping("/delete/byId")
    public void deleteDoc(){
        final Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }
    @PutMapping("/multadd/doc")
    public void saveAll(){
        List<Product> products = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Product product = new Product();
            product.setId(Long.valueOf(i));
            product.setTitle("["+i+"]小米手机");
            product.setCategory("手机");
            product.setPrice(1999.0+i);
            product.setImages("http://www.atguigu/xm.jpg");
            products.add(product);
        }
        productDao.saveAll(products);
    }

    @GetMapping("/get/page")
    public void findByPageable(){
        final Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int currentPage = 0;
        int pageSize = 5;

        final PageRequest pageReqeust = PageRequest.of(currentPage, pageSize);
        final Page<Product> productPage = productDao.findAll(pageReqeust);
        for (Product product : productPage) {
            System.out.println(product);
        }
    }
}
