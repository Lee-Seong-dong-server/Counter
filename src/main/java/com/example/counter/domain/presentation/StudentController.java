package com.example.counter.domain.presentation;


import com.example.counter.domain.repository.Dto.StudentDto;
import com.example.counter.domain.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("")
    public Long register(StudentDto studentDto){
        return studentService.register(studentDto);
    }

    @DeleteMapping("")
    public void deleteStudent(@RequestParam String studentId) throws BadRequestException {
        studentService.deleteUser(studentId);
    }

}
