package com.lovely.sun.service;

import com.lovely.sun.exception.ErrorCode;
import com.lovely.sun.exception.ResourceNotFoundException;
import com.lovely.sun.model.dto.CommonResponse;
import com.lovely.sun.model.entity.Student;
import com.lovely.sun.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<CommonResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return ResponseEntity.ok(CommonResponse.<List<Student>>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(students)
                .build());
    }

    public ResponseEntity<CommonResponse> getStudent(Long id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        return ResponseEntity.ok(CommonResponse.<Student>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .data(student)
                .build());

    }

    public ResponseEntity<CommonResponse> createStudent(Student student) {
        studentRepository.save(student);

        return success();
    }

    public ResponseEntity<CommonResponse> updateStudent(Long id, Student studentDetails) throws ResourceNotFoundException {
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

    public ResponseEntity<CommonResponse> deleteStudent(Long id) throws ResourceNotFoundException {
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
