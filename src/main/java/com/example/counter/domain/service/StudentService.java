package com.example.counter.domain.service;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.Dto.StudentDto;

public interface StudentService {
    Long register(StudentDto studentDto);
}
