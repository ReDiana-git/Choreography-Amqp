package nl.nl0e0.petclinicentity.payment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentSucessDTO {
    String recordId;
    String status;
    public PaymentSucessDTO(String recordId, String status){
        this.recordId = recordId;
        this.status = status;
    }
    public String toString(){
        return "RecordId: " + recordId + "\nstatus: " + status;
    }
}
