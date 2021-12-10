package com.yao.dydatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yao.dydatasource.entity.Student;
import com.yao.dydatasource.mapper.StudentMapper;
import com.yao.dydatasource.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiaoK
 * @date 2021/12/10
 */
@Service
@RequiredArgsConstructor
@DS("master")
public class StudentServiceImpl implements StudentService {
    public final StudentMapper mapper;

    @Override
    public void insertStudent(final Student student) {
        mapper.insert(student);
    }
}
