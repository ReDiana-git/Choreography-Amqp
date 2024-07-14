package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;

    public void createMedicine(MedicalRecord medicalRecord){
        template.convertAndSend("createMedicine", "createMedicine", medicalRecord);
    }
    public void createConsultation(MedicalRecord medicalRecord){
        template.convertAndSend("createConsultation", "createConsultation", medicalRecord);
    }
    public void createPayment(MedicalRecord medicalRecord){
        template.convertAndSend("createPayment", "createPayment", medicalRecord);
    }
}
