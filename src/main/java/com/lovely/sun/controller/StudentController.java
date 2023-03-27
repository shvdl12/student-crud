package com.lovely.sun.controller;

import com.lovely.sun.exception.ErrorCode;
import com.lovely.sun.exception.ResourceNotFoundException;
import com.lovely.sun.model.dto.CommonResponse;
import com.lovely.sun.model.entity.Student;
import com.lovely.sun.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<CommonResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return ResponseEntity.ok(CommonResponse.<List<Student>>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(students)
                .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getStudent(@PathVariable Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        return ResponseEntity.ok(CommonResponse.<Student>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(student)
                .build());

    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createStudent(@RequestBody @Valid Student student) {

        studentRepository.save(student);

        return success();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponse> updateStudent(@PathVariable(value = "id") Long id,
                                                        @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setGender(studentDetails.getGender());
        student.setNickname(studentDetails.getNickname());
        student.setEmail(studentDetails.getEmail());

        studentRepository.save(student);

        return success();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteStudent(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);

        return success();
    }

    private ResponseEntity<CommonResponse> success() {
        return ResponseEntity.ok(
                CommonResponse.builder()
                        .code(ErrorCode.SUCCESS.getCode())
                        .message(ErrorCode.SUCCESS.getMessage())
                        .build()
        );
    }
}

