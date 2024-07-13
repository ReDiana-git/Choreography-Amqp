package nl.nl0e0.medicineamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.medicine.MedicineCounterDTO;
import nl.nl0e0.petclinicentity.medicine.MedicineEntity;
import nl.nl0e0.petclinicentity.medicine.SetMedicineDTO;
import nl.nl0e0.medicineamqp.repository.MedicineRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    MedicineRepositroy repositroy;
    @Autowired
    MedicineRestTemplate restTemplate;


    public MedicineEntity createMedicine(String medicineId){
        MedicineEntity medicineEntity = new MedicineEntity(medicineId);
        repositroy.save(medicineEntity);

        return medicineEntity;
    }

    public MedicineEntity findRecordById(String recordId){
        return repositroy.findById(recordId);
    }

    public void deleteAll(){
        repositroy.deleteAll();
    }

    public MedicineCounterDTO medicineCounter(String recordId) throws IllegalAccessException {
        MedicalRecord medicalRecord = restTemplate.getRecordById(recordId);
//        if(!(medicalRecord.getState2String().equals("medicine")))
//            throw new IllegalAccessException("You are not at medicine state.");
//
//        medicalRecord.setState("done");
        restTemplate.setState(recordId, "done");
        return new MedicineCounterDTO(recordId, "done");
    }

    public MedicineEntity findMedicineByRecordId(String recordId) {
        MedicalRecord medicalRecord = restTemplate.getRecordById(recordId);
        MedicineEntity medicineEntity = repositroy.findById(medicalRecord.getMedicineId());
        return medicineEntity;
    }

    public void setMedicine(SetMedicineDTO setMedicineDTO) {
        MedicalRecord medicalRecord = restTemplate.getRecordById(setMedicineDTO.getRecordId());
        repositroy.updateMedicines(setMedicineDTO.getMedicines(), medicalRecord.getMedicineId());
    }
}
