package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.appointmentamqp.repository.MedicalRecordRepository;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    MedicalRecordRepository repository;
    public MedicalRecord createMedicalRecord(CreateAppointmentDTO createAppointmentDTO){
        MedicalRecord medicalRecord = new MedicalRecord(createAppointmentDTO);
        repository.save(medicalRecord);
        return medicalRecord;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<MedicalRecord> findByOwnerId(Integer ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public MedicalRecord findByRecordId(String recordId){
        return repository.findById(recordId);
    }

    public void updateState(MedicalRecord medicalRecord) {
        repository.updateState(medicalRecord.getState(), medicalRecord.getId());
    }
}
