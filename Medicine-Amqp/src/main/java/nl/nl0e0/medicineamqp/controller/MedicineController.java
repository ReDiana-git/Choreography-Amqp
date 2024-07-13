package nl.nl0e0.medicineamqp.controller;


import nl.nl0e0.petclinicentity.medicine.MedicineCounterDTO;
import nl.nl0e0.petclinicentity.medicine.MedicineEntity;
import nl.nl0e0.petclinicentity.medicine.SetMedicineDTO;
import nl.nl0e0.medicineamqp.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MedicineController {
    @Autowired
    MedicineService medicineService;
    @GetMapping("/appointment/medicineCounter/{recordId}")
    public ResponseEntity<?> medicineCounter(@PathVariable String recordId){
        try{
            MedicineCounterDTO medicineCounterDTO = medicineService.medicineCounter(recordId);
            System.out.println(medicineCounterDTO.getState());
            return ResponseEntity.status(HttpStatus.OK).body(medicineCounterDTO);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定義錯誤訊息和HTTP狀態碼
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }

    }
    @GetMapping("/appointment/medicine/{recordId}")
    public ResponseEntity<?> getMedicine(@PathVariable String recordId){
        MedicineEntity medicineEntity = medicineService.findMedicineByRecordId(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(medicineEntity);
    }

    @PostMapping("/appointment/medicine")
    public ResponseEntity<?> setMedicine(@RequestBody SetMedicineDTO setMedicineDTO){
        System.out.println(setMedicineDTO);
        medicineService.setMedicine(setMedicineDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/appointment/createMedicine")
    public ResponseEntity<?> createMedicine(@RequestBody String medicineId){
        medicineService.createMedicine(medicineId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/appointment/deleteMedicine")
    public ResponseEntity<?> deleteMedicine(){
        medicineService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
