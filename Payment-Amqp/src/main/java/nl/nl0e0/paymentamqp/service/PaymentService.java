package nl.nl0e0.paymentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.appointment.SetStateDTO;
import nl.nl0e0.petclinicentity.model.AppointmentState;
import nl.nl0e0.petclinicentity.payment.*;
import nl.nl0e0.paymentamqp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository repository;
    @Autowired
    PaymentRestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    AmqpSender amqpSender;

    public void createPayment(String paymentId){
        PaymentEntity payment = new PaymentEntity();
        payment.setId(paymentId);
        repository.save(payment);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Integer getPayment(String paymentId) {
        return repository.findById(paymentId).getPrice();
    }
    public void getPaymentInfo(String recordId){
        amqpSender.getRecord2Payment(recordId);
    }
    public PaymentSucessDTO payment(PaymentDTO paymentDTO){
        CardEntity cardEntity = new CardEntity(paymentDTO.getCardNumber(), paymentDTO.getCardFirstName(), paymentDTO.getCardLastName(), paymentDTO.getCheckNum());
        if(!checkCreditCardWithLuhnAlgor(cardEntity))
            throw new IllegalArgumentException("The Card Number is not available!");
        restTemplate.setState(paymentDTO.getRecordId(), "medicine");
        MedicalRecord medicalRecord = restTemplate.getRecordById(paymentDTO.getRecordId());
        return new PaymentSucessDTO(medicalRecord.getPaymentId(), medicalRecord.getState());
    }
    public boolean checkCreditCardWithLuhnAlgor(CardEntity cardEntity){
        String cardNum = cardEntity.getCardNumber();
        if(cardNum.length() != 16){
            System.out.println("The Card Num is " + cardNum.length() +" long.");
            return false;
        }
        int checkNumSum;
        if((cardNum.charAt(0) - '0') * 2 > 9)
            checkNumSum = (cardNum.charAt(0) - '0') * 2 % 10 + (cardNum.charAt(0) - '0') * 2 / 10;
        else
            checkNumSum = (cardNum.charAt(0) - '0') * 2;
        for(int i = 1; i < 15; i+=2){
            checkNumSum += cardNum.charAt(i) - '0';
            int tmp = (cardNum.charAt(i + 1) -'0') * 2;
            if(tmp > 9)
                checkNumSum = checkNumSum + tmp % 10 + tmp / 10;
            else
                checkNumSum += tmp;
        }
        return (cardNum.charAt(15) - '0') == (10 - checkNumSum % 10);
    }

    public void saveReturnMedicalRecord(MedicalRecord medicalRecord) {
        PaymentEntity paymentEntity = repository.findById(medicalRecord.getPaymentId());
        if(paymentEntity.getPrice() == null)
            paymentEntity.setPrice(0);
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO(medicalRecord.getId(), paymentEntity.getPrice(), medicalRecord.getState());
        redisTemplate.opsForValue().set(medicalRecord.getId(), paymentInfoDTO);
        System.out.println("Store data to Redis success.");
    }

    public PaymentInfoDTO reGetPaymentInfo(String recordId) {
        PaymentInfoDTO paymentInfoDTO = (PaymentInfoDTO)redisTemplate.opsForValue().getAndDelete(recordId);
        return paymentInfoDTO;
    }
}
