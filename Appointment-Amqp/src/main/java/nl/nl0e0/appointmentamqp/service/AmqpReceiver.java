package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    AmqpSender amqpSender;
    public String medicalRecordId;

    @RabbitListener(queues = "getRecord2Payment")
    public void getRecord2Payment(String medicalRecordId){
        this.medicalRecordId = medicalRecordId;
        MedicalRecord medicalRecord = medicalRecordService.findByRecordId(medicalRecordId);
        amqpSender.returnMedicine2Medicine(medicalRecord);
    }
}
