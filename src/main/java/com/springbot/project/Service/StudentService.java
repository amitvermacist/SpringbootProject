package com.springbot.project.Service;

import com.springbot.project.Entity.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentById(Long id);

    List<Student> getAllStudent();

    public String processStudent(Long stu_id, String stu_name, String stu_email, Long stu_mobile_no);

    public void saveStudent(Student student);

    public void deleteStudent(Long id);

    public Student updateStudent(Long id, Student updatedStudent);

    public Student updateStudentWithPatch(Long id, String stu_nmae, String stu_emial, Student updateStudentWithPatch);

    public List<Student> findStudentById(Long id);
}
