package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.petclinicentity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.appointment.SetStateDTO;
import nl.nl0e0.petclinicentity.model.BaseRecord;
import nl.nl0e0.petclinicentity.owner.Owner;
import nl.nl0e0.appointmentamqp.repository.AppointmentRepository;
import nl.nl0e0.appointmentamqp.repository.OwnerRepository;
import nl.nl0e0.petclinicentity.owner.OwnerNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRecordBuilder appointmentRecordBuilder;
	@Autowired
	MedicalRecordService medicalRecordService;
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AppointmentRestTemplate appointmentRestTemplate;
	@Autowired
	OwnerRepository ownerRepository;


	//客戶建立訂單
	public MedicalRecord createAppointment(CreateAppointmentDTO createAppointmentDTO){
		MedicalRecord medicalRecord = medicalRecordService.createMedicalRecord(createAppointmentDTO);
		appointmentRestTemplate.createNewRecord(medicalRecord);
		appointmentRepository.save(new AppointmentEntity(medicalRecord, createAppointmentDTO));
		return medicalRecord;
	}

	public RestTemplate getRestTemplate(){
		return this.appointmentRestTemplate.getRestTemplate();
	}


	public void checkCreateAppointmentDTOValidation(CreateAppointmentDTO createAppointMentDTO){
		String notBeNull = " should not be null.";
		if(createAppointMentDTO.getAppointmentDate() == null)
			throw new NullPointerException("Appointment Date" + notBeNull);
		if(createAppointMentDTO.getPetId() == null)
			throw new NullPointerException("Pet ID" + notBeNull);
		if(createAppointMentDTO.getOwnerId() == null)
			throw new NullPointerException("Owner ID" + notBeNull);
		if(createAppointMentDTO.getVetId() == null)
			throw new NullPointerException("Vet ID" + notBeNull);
	}
	public List<?> getAppointmentsByOwnerId(Integer owner_id){
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner_id);
        return getRecordsFromStates(MedicalRecords);
	}

	public List<?> getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
		Owner owner = ownerRepository.findByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
		List<MedicalRecord> MedicalRecords = medicalRecordService.findByOwnerId(owner.getId());
		return getRecordsFromStates(MedicalRecords);
	}

	private List<BaseRecord> getRecordsFromStates(List<MedicalRecord> medicalRecords){
		return appointmentRecordBuilder.buildRecordsFromMedicalRecords(medicalRecords);
	}

	public void deleteAll() {
		appointmentRepository.deleteAll();
		medicalRecordService.deleteAll();
		appointmentRestTemplate.deleteAllRecord();
	}

	public AppointmentEntity findAppointment(String appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

    public void setState(SetStateDTO setStateDTO) {
		MedicalRecord medicalRecord = medicalRecordService.findByRecordId(setStateDTO.getRecordId());
		if(checkChangeStateAvailable(setStateDTO ,medicalRecord.getState())){
			medicalRecord.setState(setStateDTO.getState());
			medicalRecordService.updateState(medicalRecord);
		}
		else
			throw new RuntimeException("set State denied.");

    }
	public boolean checkChangeStateAvailable(SetStateDTO setStateDTO, String currentState){
		switch (setStateDTO.getState()){
			case "consultation" :
                return currentState.equals("init");
			case "payment":
                return currentState.equals("consultation");
			case "medicine":
				return currentState.equals("payment");
			case "done":
				return currentState.equals("medicine");
			default:
				return false;
		}
	}

    public MedicalRecord getMedicalRecordById(String id) {
		return medicalRecordService.findByRecordId(id);
    }
}
