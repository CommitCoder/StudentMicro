package com.kuba.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<ErrorInfo> handleException(StudentException e){

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if(e.getStudentError().getMessage().equals(StudentError.STUDENT_EMAIL_ALREADY_EXISTS)){
            httpStatus = HttpStatus.CONFLICT;
        }else if(e.getStudentError().getMessage().equals(StudentError.STUDENT_NOT_FOUND)){
            httpStatus = HttpStatus.NOT_FOUND;
        }else if(e.getStudentError().getMessage().equals(StudentError.STUDENT_IS_NOT_ACTIVE)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorInfo(e.getStudentError().getMessage()));

    }
}
