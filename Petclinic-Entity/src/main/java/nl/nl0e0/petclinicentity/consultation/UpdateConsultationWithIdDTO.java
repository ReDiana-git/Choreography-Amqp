package nl.nl0e0.petclinicentity.consultation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UpdateConsultationWithIdDTO extends  UpdateConsultationDTO{
    public UpdateConsultationWithIdDTO(UpdateConsultationDTO dto, String medicineId, String consultationId){
        super.setSymptom(dto.getSymptom());
        super.setMedicines(dto.getMedicines());
        super.setRecordId(dto.getRecordId());
        this.consultationId = consultationId;
        this.medicineId = medicineId;
    }
    public UpdateConsultationWithIdDTO(){

    }
    private String consultationId;
    private String medicineId;
    @Override
    public String toString() {
        return "recordId : " + super.getRecordId() + "\n"
                + "medicines : " + super.getMedicines() + "\n"
                + "symptom : " + super.getSymptom() + "\n"
                + "consultationId : " + consultationId + "\n"
                + "medicineId : " + medicineId + "\n";
    }
}
