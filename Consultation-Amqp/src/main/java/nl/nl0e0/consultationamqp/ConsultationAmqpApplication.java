package nl.nl0e0.consultationamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "nl.nl0e0.petclinicentity")
public class ConsultationAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultationAmqpApplication.class, args);
    }

}
