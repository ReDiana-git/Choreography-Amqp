package nl.nl0e0.appointmentamqp.controller;

import nl.nl0e0.appointmentamqp.service.AppointmentService;
import nl.nl0e0.appointmentamqp.service.SuccessActionAlert;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.appointment.SetStateDTO;
import nl.nl0e0.petclinicentity.owner.OwnerNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    SuccessActionAlert successActionAlert = new SuccessActionAlert();


    // 建立新的預約單號
    @PostMapping("/appointment/createAppointments")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDTO createAppointMentDTO){

        try{
            appointmentService.checkCreateAppointmentDTOValidation(createAppointMentDTO);
            MedicalRecord medicalRecord = appointmentService.createAppointment(createAppointMentDTO);
            successActionAlert.createAppointmentAlert(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定義錯誤訊息和HTTP狀態碼的ResponseEntity
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    //	@PostMapping("/appointment/getAppointments")
    public ResponseEntity<?> getAppointment(@RequestBody String OwnerId){
        List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerId(Integer.valueOf(OwnerId));
        return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
    }
    @PostMapping("/appointment/getAppointments")
    public ResponseEntity<?> getAppointmentByOwnerName(@RequestBody OwnerNameDTO ownerNameDTO){
//		appointmentService.checkValid(ownerNameDTO);
        List<?> appointmentEntities = appointmentService.getAppointmentsByOwnerName(ownerNameDTO);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentEntities);
    }

    @PostMapping("/appointment/setState")
    public ResponseEntity<?> setState(@RequestBody SetStateDTO setStateDTO){
        try{
            appointmentService.setState(setStateDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteAll(){
        appointmentService.deleteAll();
        successActionAlert.deleteAppointmentsAlert();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/appointment/medicalRecord/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable("id") String id){
        MedicalRecord medicalRecord = appointmentService.getMedicalRecordById(id);
        return ResponseEntity.status(HttpStatus.OK).body(medicalRecord);
    }
}
