package com.springbot.project.Controller;

import com.springbot.project.Entity.Student;
import com.springbot.project.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            log.info("Controller class for get student by ID !");
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/process")
    public String processStudent(
            @RequestHeader("stu_id") Long stu_id,
            @RequestHeader("stu_name") String stu_name,
            @RequestHeader("stu_email") String stu_email,
            @RequestHeader("stu_mobile_no") Long stu_mobile_no) {
        return studentService.processStudent(stu_id, stu_name, stu_email, stu_mobile_no);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        log.info("Controller class for get all student data !");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        log.info("Controller class for save student data !");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/id")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id,
                                                     @RequestBody Student updatedStudent) {
        Student updateStudentData = studentService.updateStudent(id, updatedStudent);
        if (updatedStudent != null) {
            log.info("Controller class for update student data");
            return new ResponseEntity<>(updateStudentData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        log.info("Controller class for delete student data by ID !");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public Student updateStudentWithPatch(@PathVariable Long id,
                                          @RequestParam(required = false) String stu_name,
                                          @RequestParam(required = false) String stu_email,
                                          @RequestBody Student updateStudentWithPatch) {
        log.info("Cotroller class for path student using id");
        return studentService.updateStudentWithPatch(id, stu_name, stu_email, updateStudentWithPatch);
    }
}
