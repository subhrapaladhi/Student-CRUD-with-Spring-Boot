package com.example.studentcrud.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
  public List<Student> getStudents() {
    return List.of(
        new Student(
          1L,
          "subhra",
          "subhra9@gmail.com",
          LocalDate.of(2000, Month.MAY, 16),
          21
        )
    );
  }
}
