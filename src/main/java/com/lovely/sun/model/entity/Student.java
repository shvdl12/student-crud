package com.lovely.sun.model.entity;

import com.lovely.sun.annotation.Gender;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students")
@Data
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Missing require parameter 'name'")
    private String name;

    @Column(name = "age")
    @NotNull(message = "Missing require parameter 'age'")
    private Integer age;

    @Column(name = "gender")
    @NotBlank(message = "Missing require parameter 'gender'")
    @Gender
    private String gender;

    @Column(name = "nickname")
    @NotBlank(message = "Missing require parameter 'nickname'")
    private String nickname;

    @Column(name = "email", unique = true)
    @Email(message = "Not valid email format")
    @NotBlank(message = "Missing require parameter 'email'")
    private String email;
}
