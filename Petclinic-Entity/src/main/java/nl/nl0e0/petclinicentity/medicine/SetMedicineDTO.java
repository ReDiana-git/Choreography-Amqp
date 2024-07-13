package nl.nl0e0.petclinicentity.medicine;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SetMedicineDTO {
    String recordId;
    String medicines;
    public SetMedicineDTO(String recordId, String medicines){
        this.recordId = recordId;
        this.medicines = medicines;
    }
}
