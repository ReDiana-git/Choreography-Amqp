package nl.nl0e0.petclinicentity.medicine;

import lombok.Getter;

@Getter
public class MedicineCounterDTO {
    String recordId;
    String state;
    public MedicineCounterDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
}
