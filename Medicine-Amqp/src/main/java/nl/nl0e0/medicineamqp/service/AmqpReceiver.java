package nl.nl0e0.medicineamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    MedicineService medicineService;

    public MedicalRecord store;
    @RabbitListener(queues = "createMedicine")
    public void createConsultation(MedicalRecord medicalRecord){
        store = medicalRecord;
        medicineService.createMedicine(medicalRecord.getConsultationId());
    }
}
