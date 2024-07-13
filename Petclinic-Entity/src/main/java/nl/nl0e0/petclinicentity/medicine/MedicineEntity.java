package nl.nl0e0.petclinicentity.medicine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "medicine")
public class MedicineEntity implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "medicines")
    private String medicines;

    public MedicineEntity() {

    }
    public MedicineEntity(String id){
        this.id = id;
        this.medicines = "";
    }

    public MedicineEntity(MedicalRecord medicalRecord){
        this.id = medicalRecord.getMedicineId();
    }
}

