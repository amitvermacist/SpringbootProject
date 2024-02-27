package com.springbot.project.publisher;

import com.springbot.project.Config.MessageCofig;
import com.springbot.project.DTO.Student;
import com.springbot.project.DTO.StudentStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{name}")
    public String saveStudents(@RequestBody Student student, @PathVariable String name) {
        student.setId(UUID.randomUUID().toString());
        StudentStatus studentStatus = new StudentStatus(student, "Process", "Student data is in process" + name);
        rabbitTemplate.convertAndSend(MessageCofig.Exchange, MessageCofig.RoutingKey, studentStatus);
        return "Sucess !!";

    }
}
