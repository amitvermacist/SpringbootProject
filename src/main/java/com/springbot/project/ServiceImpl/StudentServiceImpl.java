package com.springbot.project.ServiceImpl;

import com.springbot.project.Entity.Student;
import com.springbot.project.Repository.StudentRepository;
import com.springbot.project.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getStudentById(Long id) {
        log.info("Service implementation for get student by id !");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public String processStudent(Long stu_id, String stu_name, String stu_email, Long stu_mobile_no) {
        return "Student Id" + stu_id + "student name" + stu_name + "student email" + stu_email + "student mobile no" + stu_mobile_no;

    }

    @Override
    public List<Student> getAllStudent() {
        log.info("Service implementation for get all student !");
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        log.info("Service implementation for save student !");
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Service implementation for delete student by id !");
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setStu_id(updatedStudent.getStu_id());
            existingStudent.setStu_name(updatedStudent.getStu_name());
            existingStudent.setStu_email(updatedStudent.getStu_email());
            existingStudent.setStu_mob_no(updatedStudent.getStu_mob_no());
            log.info("Service implementation for update student data by id !");
            return studentRepository.save(existingStudent);
        } else {
            return null;
        }
    }

    @Override
    public Student updateStudentWithPatch(Long id, String stu_name, String stu_email, Student updateStudentWithPatch) {
        Student existStudent = studentRepository.findById(id).orElse(null);
        if (existStudent != null) {
            if (updateStudentWithPatch.getStu_name() != null) {
                existStudent.setStu_name(updateStudentWithPatch.getStu_name());
            }
            if (updateStudentWithPatch.getStu_email() != null) {
                existStudent.setStu_email(updateStudentWithPatch.getStu_email());
            }
            if (updateStudentWithPatch.getStu_mob_no() != 0) {
                existStudent.setStu_mob_no((updateStudentWithPatch.getStu_mob_no()));
            }
            log.info("Service impl class fpr update student with patch");
            return studentRepository.save(existStudent);
        }
        return null;
    }
}
