package nl.nl0e0.petclinicentity.appointment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SetStateDTO {
    @Id
    String recordId;
    String state;
    public SetStateDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
    public SetStateDTO(){

    }
}
