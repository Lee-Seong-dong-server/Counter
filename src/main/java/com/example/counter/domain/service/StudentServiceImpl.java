package com.example.counter.domain.service;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public Optional<StudentEntity> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    public StudentEntity createStudent(String studentId, String name) {
        StudentEntity student = StudentEntity.builder()
                .studentId(studentId)
                .name(name)
                .bonusPoint(0)
                .minusPoint(0)
                .build();
        return studentRepository.save(student);
    }

    public Optional<StudentEntity> addBonusPoint(long id, int points) {
        return studentRepository.findById(id).map(student -> {
            student.setBonusPoint(student.getBonusPoint() + points);
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> addMinusPoint(long id, int points) {
        return studentRepository.findById(id).map(student -> {
            student.setMinusPoint(student.getMinusPoint() + points);
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> subtractBonusPoint(long id, int points) {
        return studentRepository.findById(id).map(student -> {
            student.setBonusPoint(student.getBonusPoint() - points);
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> subtractMinusPoint(long id, int points) {
        return studentRepository.findById(id).map(student -> {
            student.setMinusPoint(student.getMinusPoint() - points);
            return studentRepository.save(student);
        });
    }

    @Override
    public void reset(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException("Student not found");
        });
        entity.resetMinusPoint();
        studentRepository.save(entity);
    }

    @Override
    public void deleteUser(String studentId) throws BadRequestException {
        if(studentRepository.findByStudentId(studentId).isEmpty()){
            throw new BadRequestException();
        }
        studentRepository.deleteByStudentId(studentId);
    }

}
