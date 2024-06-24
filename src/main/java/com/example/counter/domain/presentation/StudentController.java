package com.example.counter.domain.presentation;


import com.example.counter.domain.repository.Dto.StudentDto;
import com.example.counter.domain.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("")
    public Long register(StudentDto studentDto){
        return studentService.register(studentDto);
    }
}
