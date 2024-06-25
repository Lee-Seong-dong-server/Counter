package com.example.counter.domain.service;

import com.example.counter.domain.repository.Dto.StudentDto;
import org.apache.coyote.BadRequestException;

public interface StudentService {
    void reset(Long id);

    void deleteUser(String studentId) throws BadRequestException;
}
