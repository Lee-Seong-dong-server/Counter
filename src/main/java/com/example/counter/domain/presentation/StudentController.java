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

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudent(@PathVariable long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request.getStudentId(), request.getName());
    }

    @PatchMapping("/{id}/addBonusPoint")
    public ResponseEntity<StudentEntity> addBonusPoint(@PathVariable long id, @RequestParam int points) {
        return studentService.addBonusPoint(id, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/addMinusPoint")
    public ResponseEntity<StudentEntity> addMinusPoint(@PathVariable long id, @RequestParam int points) {
        return studentService.addMinusPoint(id, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/subtractBonusPoint")
    public ResponseEntity<StudentEntity> subtractBonusPoint(@PathVariable long id, @RequestParam int points) {
        return studentService.subtractBonusPoint(id, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/subtractMinusPoint")
    public ResponseEntity<StudentEntity> subtractMinusPoint(@PathVariable long id, @RequestParam int points) {
        return studentService.subtractMinusPoint(id, points)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("")
    public void deleteStudent(@RequestParam String studentId) throws BadRequestException {
        studentService.deleteUser(studentId);
    }
}
