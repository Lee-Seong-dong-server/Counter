package com.example.counter.domain.service;

import org.apache.coyote.BadRequestException;

public interface StudentService {
    void reset(Long id);

    void deleteUser(String studentId) throws BadRequestException;
}
