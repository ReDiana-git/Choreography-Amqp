package nl.nl0e0.petclinicentity.appointment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name = "medicalrecord")
public class MedicalRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "vet_id")
    private Integer vetId;
    //	@Transient
//	@JsonIgnore
    @Column(name = "state")
    private String state;

    @Column(name = "pet_id")
    private Integer petId;
    @Column(name = "appointment_id")
    private String appointmentId = UUID.randomUUID().toString();
    @Column(name = "consultation_id")
    private String consultationId = UUID.randomUUID().toString();
    @Column(name = "payment_id")
    private String paymentId = UUID.randomUUID().toString();
    @Column(name = "medicine_id")
    private String medicineId = UUID.randomUUID().toString();


    public void setState(String state){
        this.state = state;
    }


    public MedicalRecord(CreateAppointmentDTO dto) {
        this.ownerId = dto.getOwnerId();
        this.petId = dto.getPetId();
        this.vetId = dto.getVetId();
        this.state = "init";
    }

    public MedicalRecord(){

    }
    @Override
    public String toString(){
        return "Record ID: " + id
                + "\nAppointment ID: " + appointmentId
                + "\nConsultation ID: " + consultationId
                + "\nPayment ID: " + consultationId
                + "\nMedicine ID: " + medicineId ;
    }
}

