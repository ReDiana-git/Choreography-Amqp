package nl.nl0e0.paymentamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "nl.nl0e0.petclinicentity")
public class PaymentAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentAmqpApplication.class, args);
    }

}
