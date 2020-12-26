package com.kuba.students.service;

import com.kuba.students.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student getStudent(Long id);

    List<Student> getStudents(Student.Status status);

    Student addStudent(Student student);

    void deleteStudent(Long id);

    Student putStudent(Long id, Student student);

    // modifies only field given inside body request
    Student patchStudent(Long id, Student student);



}
