package nl.nl0e0.appointmentamqp.repository;

import nl.nl0e0.petclinicentity.appointment.AppointmentEntity;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface AppointmentRepository extends Repository<AppointmentEntity,String> {

	void save(AppointmentEntity appointmentEntity);
	AppointmentEntity findById(String id);
	List<AppointmentEntity> findAll();
	void deleteAll();


}
