package com.example.counter.domain.presentation;


import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentEntity> getStudent(@PathVariable String studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request.getStudentId(), request.getName());
    }

    @PatchMapping("/{studentId}/addBonusPoint")
    public ResponseEntity<StudentEntity> addBonusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.addBonusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{studentId}/addMinusPoint")
    public ResponseEntity<StudentEntity> addMinusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.addMinusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{studentId}/subtractBonusPoint")
    public ResponseEntity<StudentEntity> subtractBonusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.subtractBonusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{studentId}/subtractMinusPoint")
    public ResponseEntity<StudentEntity> subtractMinusPoint(@PathVariable String studentId, @RequestParam int points) {
        return studentService.subtractMinusPoint(studentId, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("")
    public void deleteStudent(@RequestParam String studentId) throws BadRequestException {
        studentService.deleteUser(studentId);
    }
}
