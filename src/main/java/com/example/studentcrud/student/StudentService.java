package com.example.studentcrud.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if(studentOptional.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long id) {
    if(!studentRepository.existsById(id)){
      throw new IllegalStateException("student with id "+id+" does not exists");
    }
    studentRepository.deleteById(id);
  }

  @Transactional
  public void updateStudent(Long id, String name, String email) {
    Student student = studentRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException("student with id "+id+" not found"));

    if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
      student.setName(name);
    }
    if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if(studentOptional.isPresent()) {
        throw new IllegalStateException("Email taken");
      }
      student.setEmail(email);
    }

    studentRepository.save(student);
  }
}
