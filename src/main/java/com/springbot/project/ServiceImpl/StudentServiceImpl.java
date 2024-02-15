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
}
