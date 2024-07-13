package nl.nl0e0.petclinicentity.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseRecord {
	private String state;
	private String ownerFirstName;
	private String ownerLastName;
	private String vetFirstName;
	private String vetLastName;
	private String petName;
	private LocalDateTime appointmentDate;
	private Integer price;
}
