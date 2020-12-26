package com.kuba.students.controller;

import com.kuba.students.model.Student;
import com.kuba.students.repository.StudentRepository;
import com.kuba.students.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        System.out.println("Get student Student Controller ");
        return  studentService.getStudent(id);

    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Student.Status status ) {
        System.out.println("Get studentS  Student Controller ");
        return studentService.getStudents(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody @Valid Student student) {
        System.out.println("POST studentS  Student Controller ");

        return studentService.addStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id){
        System.out.println("DELETE studentS  Student Controller ");
        studentService.deleteStudent(id);
    }

    @PutMapping({"{id}"})
//   VALIDACJA  public ResponseEntity<Student> putStudent(@PathVariable Long id, @RequestBody @Valid Student student){
    public Student putStudent(@PathVariable Long id, @RequestBody Student student){
        System.out.println("PUT in controller ");
        return studentService.putStudent(id, student);

    }

    //patch - modyfikacja poszczególnych pól
    @PatchMapping({"{id}"})
    public Student patchStudent(@PathVariable Long id, @RequestBody Student student){
        System.out.println("PATCH in controller ");
        return studentService.patchStudent(id, student);
    }



    //aa
    //bb


    // nazwa request paramów tutaj definuje nazwe którą podajemy w postmanie ;)
//    @GetMapping("/lastname")
//    public List<Student> findStudent(@RequestParam String lastName, @RequestParam int numberOfPage){
//        Pageable pageable = PageRequest.of(numberOfPage ,2 , Sort.by("firstName"));
//        return studentRepository.findByLastName(lastName, pageable);
//    }

//
//
//    @GetMapping("/find")
//    public List<Student> findStudent2(@RequestParam String lastName, @RequestParam String firstName){
//        return studentRepository.findByLastNameAndFirstNameIsNotLikeAllIgnoreCase(lastName, firstName);
//    }
//
//    @GetMapping("/karol")
//    public List<Student> findStudent3(){
//        return studentRepository.findStudentWithNameKarol();
//    }



    // ResponseEntity - możemy dodawać dodatkowe informacje np. status kodu
//    @GetMapping("{id}")
//    public ResponseEntity<Student>getStudent(@PathVariable Long id) {
//        Optional<Student> studentOptional = studentRepository.findById(id);
//        if (studentOptional.isPresent()){
//            return ResponseEntity.ok(studentOptional.get());
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }



//    @GetMapping("{id}")
//    public Student getStudent(@PathVariable Long id) {
//        return studentRepository.findById(id).get();
//    }



}
