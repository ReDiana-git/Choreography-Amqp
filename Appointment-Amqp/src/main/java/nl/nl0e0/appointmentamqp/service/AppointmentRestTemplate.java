package nl.nl0e0.appointmentamqp.service;

import lombok.Getter;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.ConsultationEntity;
import nl.nl0e0.petclinicentity.medicine.MedicineEntity;
import nl.nl0e0.petclinicentity.payment.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;

@Component
@Getter
public class AppointmentRestTemplate {

    @Autowired
    RestTemplate restTemplate;

    String consultationUrl = "http://localhost:8081/appointment/";
    String paymentUrl = "http://localhost:8082/appointment/";
    String medicineUrl = "http://localhost:8083/appointment/";
    public void createNewRecord(MedicalRecord medicalRecord){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl + "createConsultation", medicalRecord.getConsultationId(), ConsultationEntity.class);
        ResponseEntity<PaymentEntity> paymentResult = restTemplate.postForEntity(paymentUrl + "createPayment", medicalRecord.getPaymentId(), PaymentEntity.class);
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.postForEntity(medicineUrl + "createMedicine", medicalRecord.getMedicineId(), MedicineEntity.class);
    }
    public void createConsultation(String consultationId){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl + "createConsultation", consultationId, ConsultationEntity.class);

    }
    public void deleteAllRecord(){
        ResponseEntity<ConsultationEntity> consultationResult = restTemplate.postForEntity(consultationUrl + "deleteConsultation", null, ConsultationEntity.class);
        ResponseEntity<PaymentEntity> paymentResult = restTemplate.postForEntity(paymentUrl + "deletePayment", null, PaymentEntity.class);
        ResponseEntity<MedicineEntity> medicineResult = restTemplate.postForEntity(medicineUrl + "deleteMedicine", null, MedicineEntity.class);
    }


}
