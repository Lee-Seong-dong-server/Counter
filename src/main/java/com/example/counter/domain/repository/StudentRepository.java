package com.example.counter.domain.repository;

import com.example.counter.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    void deleteByStudentId(String studentId);

    Optional<StudentEntity> findByStudentId(String studentId);

    void deleteAll();
}
