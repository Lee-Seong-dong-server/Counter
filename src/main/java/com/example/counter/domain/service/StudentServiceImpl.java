package com.example.counter.domain.service;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentEntity> getHighBonusPointStudents() {
        List<StudentEntity> students = new ArrayList<>(studentRepository.findAll());
        students.sort((a, b) -> b.getBonusPoint() - a.getBonusPoint());
        return students;
    }

    public Optional<StudentEntity> getStudentById(String userId) {
        return studentRepository.findByStudentId(userId);
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

    public Optional<StudentEntity> addBonusPoint(long studentId, int points, String reason) {
        return studentRepository.findById(studentId).map(student -> {
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

    public Optional<StudentEntity> addMinusPoint(long studentId, int points, String reason) {
        return studentRepository.findById(studentId).map(student -> {
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

    public Optional<StudentEntity> subtractBonusPoint(String studentId, int points) {
        return studentRepository.findByStudentId(studentId).map(student -> {
            student.setBonusPoint(student.getBonusPoint() - points);
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> subtractMinusPoint(String studentId, int points) {
        return studentRepository.findByStudentId(studentId).map(student -> {
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

    @Transactional
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
