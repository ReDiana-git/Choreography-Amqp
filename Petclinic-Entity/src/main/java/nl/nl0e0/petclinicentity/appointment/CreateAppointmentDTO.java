package nl.nl0e0.petclinicentity.appointment;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ownerId;
	private Integer petId;
	private Integer vetId;
	private LocalDateTime appointmentDate;

}
