package com.example.counter.domain.repository;

import com.example.counter.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    void deleteByStudentId(String studentId);

    // Optional이란?
    // npe(nullPointerException)같은 에러를 방지해주는거에요
    Optional<StudentEntity> findByStudentId(String studentId);

    void deleteAll();
}
