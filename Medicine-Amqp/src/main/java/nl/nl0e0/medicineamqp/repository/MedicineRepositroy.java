package nl.nl0e0.medicineamqp.repository;

import jakarta.transaction.Transactional;
import nl.nl0e0.petclinicentity.medicine.MedicineEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MedicineRepositroy extends Repository<MedicineEntity,String> {
	void save(MedicineEntity medicineEntity);
	@Modifying
	@Query("UPDATE MedicineEntity medicineEntity SET medicineEntity.medicines = :medicines WHERE medicineEntity.id = :medicineId")
	@Transactional
	void updateMedicines(@Param("medicines") String medicines, @Param("medicineId") String medicineId);


	void deleteMedicineEntitiesById(String medicineId);

	MedicineEntity findById(String medicineId);

    void deleteAll();
}
