package com.kuba.students.service;

import com.kuba.students.exception.StudentError;
import com.kuba.students.exception.StudentException;
import com.kuba.students.model.Student;
import com.kuba.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));

        if(!Student.Status.ACTIVE.equals(student.getStatus())){
            throw new  StudentException(StudentError.STUDENT_IS_NOT_ACTIVE);
        }

        return student;

    }

    @Override
    public List<Student> getStudents( Student.Status status) {
        if(status != null){
            return studentRepository.findAllByStatus(status);
        }else{
            return studentRepository.findAll();
        }
    }

    @Override
    public Student addStudent(Student student) {

        validateStudentEmailExists(student);

        if(studentRepository.existsByEmail(student.getEmail())){
            throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        // jeśli nie znalezliśmy studenta rzucamy wyjątkiem
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentException(StudentError.STUDENT_NOT_FOUND));
//        studentRepository.deleteById(id);  to też zadziała

        student.setStatus(Student.Status.INACTIVE);
        studentRepository.save(student);
//        studentRepository.delete(student);
    }

    @Override
    public Student putStudent(Long id, Student student) {

        System.out.println("PUT  student service impl");

       return studentRepository.findById(id)
                .map(studentFromDb ->{
//                    validateStudentEmailExists(student);
                    if(studentRepository.existsByEmail(student.getEmail()) &&
                        !studentFromDb.getEmail().equals(student.getEmail())){
                        throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
                    }
                    studentFromDb.setFirstName(student.getFirstName());
                    studentFromDb.setLastName(student.getLastName());
                    studentFromDb.setEmail(student.getEmail());
                    studentFromDb.setStatus(student.getStatus());
                    return studentRepository.save(studentFromDb);
                }).orElseThrow(()-> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }

    @Override
    public Student patchStudent(Long id, Student student) {

        // Should I add possibility to change only email using patch method, I'm using id in patch url request
        // Issue to consider

        System.out.println("PATCH student serviceImpl ");

        return studentRepository.findById(id)
                .map(studentFromDb ->{
                    if(!StringUtils.isEmpty(student.getFirstName())){
                        studentFromDb.setFirstName(student.getFirstName());
                    }
                    if(!StringUtils.isEmpty(student.getLastName())){
                        studentFromDb.setLastName(student.getLastName());
                    }
                    if(!StringUtils.isEmpty(student.getStatus())){
                        studentFromDb.setStatus(student.getStatus());
                    }

                    return studentRepository.save(studentFromDb);
                    }).orElseThrow(()-> new StudentException(StudentError.STUDENT_NOT_FOUND));
    }




    private void validateStudentEmailExists(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new StudentException(StudentError.STUDENT_EMAIL_ALREADY_EXISTS);
        }
    }





}
