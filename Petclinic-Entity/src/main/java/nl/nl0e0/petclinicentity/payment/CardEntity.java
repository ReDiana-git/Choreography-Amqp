package nl.nl0e0.petclinicentity.payment;

import lombok.Getter;

@Getter
public class CardEntity {
    String cardNumber;
    String cardFirstName;
    String cardLastName;
    String checkNum;
    public CardEntity(String cardNumber, String cardFirstName, String cardLastName, String checkNum){
        this.cardNumber = cardNumber;
        this.cardFirstName = cardFirstName;
        this.cardLastName = cardLastName;
        this.checkNum = checkNum;
    }
    public CardEntity(String cardNumber){
        this.cardNumber = cardNumber;
    }
}
