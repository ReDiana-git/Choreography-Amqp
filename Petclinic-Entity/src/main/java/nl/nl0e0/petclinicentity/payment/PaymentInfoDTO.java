package nl.nl0e0.petclinicentity.payment;

public class PaymentInfoDTO {
    String recordId;
    Integer price;
    String state;
    public PaymentInfoDTO(String recordId, Integer price, String state){
        this.recordId = recordId;
        this.price = price;
        this.state = state;
    }
}
