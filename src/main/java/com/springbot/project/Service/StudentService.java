package com.springbot.project.Service;

import com.springbot.project.Entity.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentById(Long id);
    List<Student> getAllStudent();
    public  void saveStudent(Student student);
    public void deleteStudent(Long id);
    public Student updateStudent(Long id,Student updatedStudent);
}
