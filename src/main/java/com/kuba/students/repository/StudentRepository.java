package com.kuba.students.repository;

import com.kuba.students.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findByLastName(String lastName, Pageable pageable);

    boolean existsByEmail(String email);

    List<Student> findAllByStatus(Student.Status status);

//
//    List<Student> findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(String lastName, String firstName);
//
//    @Query("Select s from Student s where s.firstName = 'Karol' ")
//    List<Student> findStudentWithNameKarol();

}
