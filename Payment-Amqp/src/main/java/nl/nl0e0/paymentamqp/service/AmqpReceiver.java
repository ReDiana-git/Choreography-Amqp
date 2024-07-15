package nl.nl0e0.paymentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    PaymentService paymentService;

    public MedicalRecord store;
    @RabbitListener(queues = "createPayment")
    public void createConsultation(MedicalRecord medicalRecord){
        store = medicalRecord;
        paymentService.createPayment(medicalRecord.getPaymentId());
    }
    @RabbitListener(queues = "returnMedicine2Medicine")
    public void returnMedicine2Medicine(MedicalRecord medicalRecord){
        store = medicalRecord;
        paymentService.saveReturnMedicalRecord(medicalRecord);
    }
}
