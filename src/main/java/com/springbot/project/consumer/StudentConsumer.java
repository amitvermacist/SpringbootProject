package com.springbot.project.consumer;

import com.springbot.project.Config.MessageCofig;
import com.springbot.project.DTO.StudentStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StudentConsumer {

    @RabbitListener(queues = MessageCofig.Queue)
    public void consumerMessageFromQueue(StudentStatus studentStatus) {
        System.out.println("Message recevied from queue :" + studentStatus);

    }
}
