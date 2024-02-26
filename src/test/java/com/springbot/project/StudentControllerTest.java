package com.springbot.project;

import com.springbot.project.Controller.StudentController;
import com.springbot.project.Entity.Student;
import com.springbot.project.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void getAllStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "amit", "amitvermacist@gmail.com", 8969779109L));
        students.add(new Student(2L, "Aman", "aman@gmail.com", 8969009109L));
        when(studentService.getAllStudent()).thenReturn(students);

        ResponseEntity<List<Student>> responseEntity = studentController.getAllStudent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void getStudentById() {
        Student student = new Student(1L, "amit", "amitvermacist@gmail.com", 8969779109L);
        when(studentService.getStudentById(1L)).thenReturn(student);

        ResponseEntity<Student> responseEntity = studentController.getStudentById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("amit", responseEntity.getBody().getStu_name());
    }

    @Test
    public void saveStudent() {
        Student student = new Student();
        doNothing().when(studentService).saveStudent(any(Student.class));
        ResponseEntity<Student> responseEntity = studentController.saveStudent(student);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    public void deleteStudent() {
        Long stu_id = 1L;
        ResponseEntity<Void> responseEntity = studentController.deleteStudent(stu_id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(studentService, times(1)).deleteStudent(stu_id);
    }

    @Test
    public void updateStudent() {
        Long studentId = 1L;
        Student student = new Student();
        when(studentService.updateStudent(eq(studentId), any(Student.class))).thenReturn(student);
        ResponseEntity<Student> responseEntity = studentController.updateStudentById(studentId, student);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    public void updateStudentWithPatch() {
        Long studentId = 1L;
        String stuName = "amit";
        String stuEmail = "amitvermacist@gmail.com";

        Student updateStudent = new Student();
        updateStudent.setStu_name(stuName);
        updateStudent.setStu_email(stuEmail);
        updateStudent.setStu_mob_no(1234567890L);

        when(studentService.updateStudentWithPatch(eq(studentId), eq(stuName), eq(stuEmail), any(Student.class)))
                .thenReturn(updateStudent);

        Student updatedStudent = studentController.updateStudentWithPatch(studentId, stuName, stuEmail, updateStudent);

        assertEquals(stuName, updatedStudent.getStu_name());
        assertEquals(stuEmail, updatedStudent.getStu_email());
        assertEquals(1234567890, updatedStudent.getStu_mob_no());
    }
}
