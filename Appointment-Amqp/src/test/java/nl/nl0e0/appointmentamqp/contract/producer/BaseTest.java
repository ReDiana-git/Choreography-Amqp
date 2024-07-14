package nl.nl0e0.appointmentamqp.contract.producer;

import nl.nl0e0.appointmentamqp.AppointmentAmqpApplication;
import nl.nl0e0.appointmentamqp.controller.AppointmentController;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.noop.NoOpStubMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, AppointmentAmqpApplication.class}, properties = "stubrunner.amqp.mockConnection=false")
@Testcontainers
@AutoConfigureMessageVerifier
public class BaseTest {
    @Container
    static RabbitMQContainer rabbit = new RabbitMQContainer();

    @DynamicPropertySource
    static void rabbitProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
    }

    @Autowired
    AppointmentController appointmentController;

    public void createAppointmentTrigger(){
        String dateTimeString = "2024-07-05T14:45:33.851107";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        CreateAppointmentDTO dto = new CreateAppointmentDTO();
        dto.setAppointmentDate(dateTime);
        dto.setPetId(2);
        dto.setOwnerId(1);
        dto.setVetId(1);
        appointmentController.createAppointment(dto);
    }
}
class RabbitMessageVerifier implements MessageVerifierReceiver<Message> {
    private static final Logger log = LoggerFactory.getLogger(RabbitMessageVerifier.class);

    private final LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();


    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
//        log.info("Clear the blockingQueue!");
//        queue.clear();
        try {
            return queue.poll(timeout, timeUnit);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @RabbitListener(queues = "createConsultation")
    public void listen4consultation(Message message) {
        log.info("Got a message from createConsultation! [{}]", message);
        queue.add(message);
    }
    @RabbitListener(queues = "createPayment")
    public void listen4payment(Message message) {
        log.info("Got a message from createPayment! [{}]", message);
        queue.add(message);
    }
    @RabbitListener(queues = "createMedicine")
    public void listen4medicine(Message message) {
        log.info("Got a message from createMedicine! [{}]", message);
        queue.add(message);
    }

    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 1, TimeUnit.SECONDS, contract);
    }
}
@Configuration
class TestConfig{
    @Bean
    RabbitMessageVerifier rabbitTemplateMessageVerifier() {
        return new RabbitMessageVerifier();
    }
    @Bean
    ContractVerifierMessaging<Message> rabbitContractVerifierMessaging(RabbitMessageVerifier messageVerifier) {
        return new ContractVerifierMessaging<>(new NoOpStubMessages<>(), messageVerifier) {

            @Override
            protected ContractVerifierMessage convert(Message message) {
                if (message == null) {
                    return null;
                }
                return new ContractVerifierMessage(message.getPayload(), message.getHeaders());
            }

        };
    }


}
