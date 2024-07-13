package nl.nl0e0.petclinicentity.owner;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerNameDTO {

    String firstName;

    String lastName;

    @Override
    public String toString(){
        return "first name is " + firstName + "\nlast name is " + lastName;
    }
}
