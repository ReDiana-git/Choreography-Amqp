package nl.nl0e0.petclinicentity.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfoDTO {
    String recordId;
    Integer price;
    String state;
    public PaymentInfoDTO(){

    }
    public PaymentInfoDTO(String recordId, Integer price, String state){
        this.recordId = recordId;
        this.price = price;
        this.state = state;
    }
}
