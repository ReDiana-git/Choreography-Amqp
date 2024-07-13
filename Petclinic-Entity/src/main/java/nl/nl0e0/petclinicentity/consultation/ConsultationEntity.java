package nl.nl0e0.petclinicentity.consultation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "consultation")
public class ConsultationEntity implements Serializable {

    public ConsultationEntity() {
    }

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "symptom")
    private String symptom;

    public ConsultationEntity(MedicalRecord medicalRecord){
        this.id = medicalRecord.getConsultationId();
    }

}
