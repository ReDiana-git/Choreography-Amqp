package nl.nl0e0.paymentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;

    public void getRecord2Payment(String recordId){
        template.convertAndSend("getRecord2Payment", "getRecord2Payment", recordId);
        System.out.println("Send message to getRecord2Payment.");
    }
}