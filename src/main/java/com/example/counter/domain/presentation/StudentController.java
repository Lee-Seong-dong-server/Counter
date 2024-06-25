package com.example.counter.domain.presentation;


import com.example.counter.domain.entity.StudentEntity;
import com.example.counter.domain.repository.Dto.StudentDto;
import com.example.counter.domain.service.StudentService;
import com.example.counter.domain.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping("")
    public void deleteStudent(@RequestParam String studentId) throws BadRequestException {
        studentService.deleteUser(studentId);
    }
}
