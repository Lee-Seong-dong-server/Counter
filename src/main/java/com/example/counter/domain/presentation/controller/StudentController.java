package com.example.counter.domain.presentation.controller;

import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.presentation.dto.ReasonRequest;
import com.example.counter.domain.presentation.dto.StudentRequest;
import com.example.counter.domain.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final에 생성자 생성
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping
    public List<StudentEntity> getAllStudents() { // 모든 학생 불러오기
        return studentService.getAllStudents();
    }

    @GetMapping("/highBonusPoint") // 높은 상점순으로 불러오기
    public List<StudentEntity> getHighBonusPointStudents() {
        return studentService.getHighBonusPointStudents();
    }

    @GetMapping("/{studentId}") // 1명의 학생만 가져오기
    public ResponseEntity<StudentEntity> getStudent(@PathVariable String studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // http 상태 코드 반환, ex) 404
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentRequest request) { // 학생 생성
        return studentService.createStudent(request.getStudentId(), request.getName());
    }

    @PatchMapping("/{studentId}/addBonusPoint") // 상점 추가
    public ResponseEntity<StudentEntity> addBonusPoint(@PathVariable long studentId, @RequestParam int points, @RequestBody ReasonRequest reason) {
        return studentService.addBonusPoint(studentId, points, reason.getReason())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // http 상태 코드 반환, ex) 404
    }

    @PatchMapping("/{studentId}/addMinusPoint") // 벌점 추가
    public ResponseEntity<StudentEntity> addMinusPoint(@PathVariable long studentId, @RequestParam int points, @RequestBody ReasonRequest reason) {
        return studentService.addMinusPoint(studentId, points, reason.getReason())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // http 상태 코드 반환, ex) 404
    }

    @PatchMapping("/{studentId}/subtractBonusPoint") // 상점 삭감
    public ResponseEntity<StudentEntity> subtractBonusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.subtractBonusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // http 상태 코드 반환, ex) 404
    }

    @PatchMapping("/{studentId}/subtractMinusPoint") // 벌점 삭감(상쇄? 느낌)
    public ResponseEntity<StudentEntity> subtractMinusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.subtractMinusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // http 상태 코드 반환, ex) 404
    } // 이것도 생각해보세요

    @DeleteMapping("/{studentId}/deleteUser") // 유저 1명 삭제
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) throws BadRequestException {
        studentService.deleteUser(studentId);
        return ResponseEntity.ok("학생 제거 완료");
    }

    @DeleteMapping("/deleteAll") // 학생 전체 삭제
    public ResponseEntity<String> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok("전체 학생 제거 완료"); // 이런식으로 controller 에는 그저 service 에 보내고, 성공했는지 알려주기만.
    }
}
