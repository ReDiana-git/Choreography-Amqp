package nl.nl0e0.consultationamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.CheckConsultationDTO;
import nl.nl0e0.petclinicentity.consultation.ConsultationEntity;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicentity.medicine.MedicineEntity;
import nl.nl0e0.consultationamqp.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    @Autowired
    ConsultationRestTemplate consultationRestTemplate;

    public void createConsultation(String consultationId){
        ConsultationEntity consultationEntity = new ConsultationEntity();
        consultationEntity.setId(consultationId);
        consultationEntity.setSymptom("");
        consultationRepository.save(consultationEntity);
    }




    public void deleteAll() {
        consultationRepository.deleteAll();
    }

    public CheckConsultationDTO checkConsultation(String recordId) {
        MedicalRecord record = consultationRestTemplate.getRecordById(recordId);
        System.out.println(record);
        ConsultationEntity consultationEntity = consultationRepository.findById(record.getConsultationId());
        MedicineEntity medicineEntity = consultationRestTemplate.getMedicineById(recordId);
        consultationRestTemplate.setState(recordId, "consultation");
        return new CheckConsultationDTO(consultationEntity.getSymptom(),
                medicineEntity.getMedicines(),
                record.getOwnerId(),
                record.getPetId(),
                record.getState());
    }

    public void updateConsultation(UpdateConsultationDTO updateConsultationDTO) {
        MedicalRecord medicalRecord = consultationRestTemplate.getRecordById(updateConsultationDTO.getRecordId());
        consultationRestTemplate.setMedicine(medicalRecord.getId(),updateConsultationDTO.getMedicines());
        consultationRepository.updateSymptom(medicalRecord.getConsultationId(), updateConsultationDTO.getSymptom());
        consultationRestTemplate.setState(medicalRecord.getId(), "payment");
    }

}
