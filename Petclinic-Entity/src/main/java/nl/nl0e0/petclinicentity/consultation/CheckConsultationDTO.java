package nl.nl0e0.petclinicentity.consultation;

import lombok.Getter;


@Getter
public class CheckConsultationDTO {
	// 客戶查看病狀態
	// 會回覆客戶此病歷的金額
	private String symptom;
	private String medicines;
	private Integer ownerId;
	private Integer petId;
	private String state;

	public CheckConsultationDTO(String symptom, String medicines, Integer ownerId, Integer petId, String state){
		this.symptom = symptom;
		this.medicines = medicines;
		this.ownerId = ownerId;
		this.petId = petId;
		this.state = state;
	}
}
