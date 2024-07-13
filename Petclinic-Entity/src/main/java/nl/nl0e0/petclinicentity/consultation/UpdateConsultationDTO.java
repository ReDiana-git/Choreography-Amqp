package nl.nl0e0.petclinicentity.consultation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateConsultationDTO {

    private String recordId;
    private String medicines;
    private String symptom;

    @Override
    public String toString() {
        return "recordId : " + recordId + "\n"
                + "medicines : " + medicines + "\n"
                + "symptom : " + symptom;
    }
}
