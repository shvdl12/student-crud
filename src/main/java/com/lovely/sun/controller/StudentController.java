package com.lovely.sun.controller;

import com.lovely.sun.exception.ResourceNotFoundException;
import com.lovely.sun.model.dto.CommonResponse;
import com.lovely.sun.model.entity.Student;
import com.lovely.sun.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getStudent(@PathVariable Long id) throws ResourceNotFoundException {
        return studentService.getStudent(id);
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createStudent(@RequestBody @Valid Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponse> updateStudent(@PathVariable(value = "id") Long id,
                                                        @RequestBody Student studentDetails) throws ResourceNotFoundException {
        return studentService.updateStudent(id, studentDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteStudent(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return studentService.deleteStudent(id);
    }


}

