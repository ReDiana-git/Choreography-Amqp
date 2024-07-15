//package nl.nl0e0.paymentamqp.consumer;
//
//import nl.nl0e0.paymentamqp.PaymentAmqpApplication;
//import nl.nl0e0.paymentamqp.service.AmqpReceiver;
//import org.assertj.core.api.BDDAssertions;
//import org.awaitility.Awaitility;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.contract.stubrunner.StubTrigger;
//import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
//import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
//import org.springframework.cloud.contract.verifier.converter.YamlContract;
//import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.RabbitMQContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import javax.annotation.Nullable;
//import java.nio.charset.StandardCharsets;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, PaymentAmqpApplication.class}, properties = "stubrunner.amqp.mockConnection=false")
//@AutoConfigureStubRunner(ids = "nl.nl0e0:Appointment-Amqp", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@Testcontainers
//@ActiveProfiles("test")
//public class ApplicationTests {
//
//
//    @Container
//    static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3-management")
//            .withExposedPorts(5672, 15672);  // 暴露 AMQP 和管理界面的端口
////	@Container static RabbitMQContainer rabbit = new RabbitMQContainer();
//
//    @DynamicPropertySource
//    static void rabbitProperties(DynamicPropertyRegistry registry) {
//        rabbit.start();
//        registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
//        registry.add("spring.rabbitmq.management.port", () -> rabbit.getMappedPort(15672));
//    }
//
//    @Autowired
//    StubTrigger trigger;
//    @Autowired
//    AmqpReceiver amqpReceiver;
//    @Autowired
//    private RabbitTemplate template;
//
//    @Test
//    public void contextLoads() {
//        this.trigger.trigger("send_message_to_payment");
//
////		template.convertAndSend("createAppointmentExchange", "createAppointment", new CreateAppointmentDTO());
//
//        // 打印实际映射的管理界面端口
//        System.out.println("RabbitMQ Management Interface is available at http://localhost:" + rabbit.getMappedPort(15672));
//
//        Awaitility.await().atMost(Duration.ofMinutes(10)).untilAsserted(() -> {
//            BDDAssertions.then(this.amqpReceiver.store).isNotNull();
//            BDDAssertions.then(this.amqpReceiver.store.getOwnerId()).isEqualTo(1);
//        });
//
//    }
//}
//@Configuration
//class TestConfig{
//    private static final Logger log = LoggerFactory.getLogger(TestConfig.class);
//    @Bean
//    MessageVerifierSender<Message<?>> testMessageVerifier(RabbitTemplate rabbitTemplate) {
//        return new MessageVerifierSender<>() {
//
//            @Override
//            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
//                log.info("Sending a message to destination [{}]", destination);
//                System.out.println("Sending a message" + message);
////				rabbitTemplate.convertAndSend("createAppointmentExchange", destination, message);
//                rabbitTemplate.send(destination, toMessage(message));
//            }
//
//            @Override
//            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
//                log.info("Sending a message to destination [{}]", destination);
//                send(org.springframework.messaging.support.MessageBuilder.withPayload(payload).copyHeaders(headers).build(), destination, contract);
//            }
//
//            private org.springframework.amqp.core.Message toMessage(Message<?> msg) {
//                Object payload = msg.getPayload();
//                MessageHeaders headers = msg.getHeaders();
//                Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
//                MessageProperties messageProperties = new MessageProperties();
//                newHeaders.forEach(messageProperties::setHeader);
//                if (payload instanceof String) {
//                    String json = (String) payload;
//                    org.springframework.amqp.core.Message message = MessageBuilder.withBody(json.getBytes(StandardCharsets.UTF_8)).andProperties(messageProperties).build();
//                    return message;
//                } else {
//                    throw new IllegalStateException("Payload is not a String");
//                }
//            }
//        };
//
//    }
//}