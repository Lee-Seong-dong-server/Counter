package com.example.counter.domain.service;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final에 생성자 생성
public class StudentServiceImpl implements StudentService { // StudentService 인터페이스 구현부
    private final StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents() { // 모든 학생 불러오기
        return studentRepository.findAll();
    }

    public List<StudentEntity> getHighBonusPointStudents() { // 상점 내림차순으로 불러오기
        List<StudentEntity> students = new ArrayList<>(studentRepository.findAll()); // 전체 학생 리스트 생성
        students.sort((a, b) -> b.getBonusPoint() - a.getBonusPoint()); // 두 객체를 비교해서 내림차순으로 만든다
        return students;
    }

    public Optional<StudentEntity> getStudentById(String userId) { // 학생 1명 불러옴
        return studentRepository.findByStudentId(userId);
    }

    public StudentEntity createStudent(String studentId, String name) {
        StudentEntity student = StudentEntity.builder() // 빌더 생성
                .studentId(studentId) // StudentEntity의 studentId에 설정
                .name(name) // StudentEntity의 name에 설정
                .bonusPoint(0) // 0으로 초기화
                .minusPoint(0) // 0으로 초기화
                .build(); // StudentEntity 객체 생성
        return studentRepository.save(student); // 저장 후 반환
    }

    public Optional<StudentEntity> addBonusPoint(long studentId, int points, String reason) { // 상점 추가
        return studentRepository.findById(studentId).map(student -> {
            // pointReasons가 비어있지 않으면 세미콜론으로 구분해서 추가
            if (student.getPointReasons() == null) {
                student.setPointReasons("[상점]"+reason);
            } else {
                student.setPointReasons(student.getPointReasons() + ", [상점]" + reason);
            }
            student.setBonusPoint(student.getBonusPoint() + points);
            // 저장 후 반환
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> addMinusPoint(long studentId, int points, String reason) { // 벌점 추가
        return studentRepository.findById(studentId).map(student -> {
            if (student.getPointReasons() == null) {
                student.setPointReasons("[벌점]"+reason);
            } else {
                student.setPointReasons(student.getPointReasons() + ", [벌점]" + reason);
            }
            student.setMinusPoint(student.getMinusPoint() + points);
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> subtractBonusPoint(String studentId, int points) { // 상점 삭감
        return studentRepository.findByStudentId(studentId).map(student -> {
            student.setBonusPoint(student.getBonusPoint() - points); // 원래 point에서 깎는다.
            return studentRepository.save(student);
        });
    }

    public Optional<StudentEntity> subtractMinusPoint(String studentId, int points) { // 벌점 삭감
        return studentRepository.findByStudentId(studentId).map(student -> {
            student.setMinusPoint(student.getMinusPoint() - points); // 원래 point에서 깎는다.
            return studentRepository.save(student);
        });
    }

    @Override
    public void reset(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElseThrow(()-> { // http 상태 코드 반환
            throw new RuntimeException("Student not found");
        });
        entity.resetMinusPoint();
        studentRepository.save(entity);
    }

    @Override
    @Transactional // 데이터베이스 일관성 유지 (성공하거나, 실패하거나)
    public void deleteUser(String studentId) throws BadRequestException {
        if(studentRepository.findByStudentId(studentId).isEmpty()){
            throw new BadRequestException();
        }
        studentRepository.deleteByStudentId(studentId);
    }

    @Transactional // 데이터베이스 일관성 유지 (성공하거나, 실패하거나)
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
