package nl.nl0e0.paymentamqp.controller;


import nl.nl0e0.petclinicentity.payment.PaymentDTO;
import nl.nl0e0.petclinicentity.payment.PaymentInfoDTO;
import nl.nl0e0.petclinicentity.payment.PaymentSucessDTO;
import nl.nl0e0.paymentamqp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/appointment/getPaymentInfo")
    public ResponseEntity<?> getPaymentInfo(@RequestBody String recordId){
//        System.out.println(recordId);
        paymentService.getPaymentInfo(recordId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/appointment/reGetPaymentInfo")
    public ResponseEntity<?> reGetPaymentInfo(@RequestParam("recordId") String recordId){
        PaymentInfoDTO paymentInfoDTO = paymentService.reGetPaymentInfo(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(paymentInfoDTO);
    }

    @PostMapping("/appointment/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentDTO paymentDTO){
        try{
            PaymentSucessDTO paymentSucessDTO = paymentService.payment(paymentDTO);
            System.out.println(paymentSucessDTO.toString());
            return ResponseEntity.status(HttpStatus.OK).body(paymentSucessDTO);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    // 讓 Appointment microservice 可以新增 Payment
    @PostMapping("/appointment/createPayment")
    public ResponseEntity<?> createPayment(@RequestBody String paymentId){
        paymentService.createPayment(paymentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/appointment/deletePayment")
    public ResponseEntity<?> deletePayment(){
        paymentService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
