package com.yao.dydatasource.controller;

import com.yao.dydatasource.entity.City;
import com.yao.dydatasource.entity.Student;
import com.yao.dydatasource.service.CityService;
import com.yao.dydatasource.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaoK
 * @date 2021/12/10
 */
@Controller
@RequiredArgsConstructor
public class TotalController {
    public final CityService cityService;
    public final StudentService studentService;

    @GetMapping("/test")
    public void test() {
        City city = City.builder().id("1111").cityName("shanghai").address("上海").build();
        cityService.insertCity(city);
        Student zhangsan = Student.builder().id("2222").userName("zhangsan").age(12).build();
        studentService.insertStudent(zhangsan);
    }
}
