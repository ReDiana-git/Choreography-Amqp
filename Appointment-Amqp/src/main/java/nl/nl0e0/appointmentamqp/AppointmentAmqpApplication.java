package nl.nl0e0.appointmentamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "nl.nl0e0.petclinicentity")
public class AppointmentAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentAmqpApplication.class, args);
    }

}
