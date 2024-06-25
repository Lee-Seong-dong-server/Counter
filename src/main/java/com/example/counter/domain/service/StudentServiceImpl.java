package com.example.counter.domain.service;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.Dto.StudentDto;
import com.example.counter.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Optional<StudentEntity> addBonusPoint(long id, int points, String reason) {
        return studentRepository.findById(id).map(student -> {
            String pointReasons = student.getPointReasons();
            // pointReasons가 비어있지 않으면 세미콜론으로 구분해서 추가
            if (pointReasons.isEmpty()) {
                student.setPointReasons("[상점]"+reason);
            } else {
                student.setPointReasons(student.getPointReasons() + ", [상점]" + reason);
            }
            student.setBonusPoint(student.getBonusPoint() + points);
            // 저장 후 반환
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> addMinusPoint(long id, int points, String reason) {
        return studentRepository.findById(id).map(student -> {
            String pointReasons = student.getPointReasons();
            if (pointReasons.isEmpty()) {
                student.setPointReasons("[벌점]"+reason);
            } else {
                student.setPointReasons(student.getPointReasons() + ", [벌점]" + reason);
            }

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
