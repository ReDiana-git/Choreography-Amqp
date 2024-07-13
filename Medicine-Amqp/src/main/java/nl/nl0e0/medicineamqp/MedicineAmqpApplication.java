package nl.nl0e0.medicineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "nl.nl0e0.petclinicentity")
public class MedicineAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicineAmqpApplication.class, args);
    }

}
