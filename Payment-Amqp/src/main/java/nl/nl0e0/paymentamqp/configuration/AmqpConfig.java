package nl.nl0e0.paymentamqp.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue createConsultationQueue() {
        return new Queue("createConsultation", false);
    }

    @Bean
    public Exchange createConsultationExchange()
    {
        return new DirectExchange("createConsultation");
    }

    @Bean
    public Binding bindingCreateConsultation(Queue createConsultationQueue, Exchange createConsultationExchange)
    {
        return BindingBuilder.bind(createConsultationQueue)
                .to(createConsultationExchange)
                .with("createConsultation")
                .noargs();
    }
    @Bean
    public Queue createPaymentQueue() {
        return new Queue("createPayment", false);
    }

    @Bean
    public Exchange createPaymentExchange()
    {
        return new DirectExchange("createPayment");
    }

    @Bean
    public Binding bindingCreatePayment(Queue createPaymentQueue, Exchange createPaymentExchange)
    {
        return BindingBuilder.bind(createPaymentQueue)
                .to(createPaymentExchange)
                .with("createPayment")
                .noargs();
    }
    @Bean
    public Queue createMedicineQueue() {
        return new Queue("createMedicine", false);
    }

    @Bean
    public Exchange createMedicineExchange()
    {
        return new DirectExchange("createMedicine");
    }

    @Bean
    public Binding bindingCreateMedicine(Queue createMedicineQueue, Exchange createMedicineExchange)
    {
        return BindingBuilder.bind(createMedicineQueue)
                .to(createMedicineExchange)
                .with("createMedicine")
                .noargs();
    }
    @Bean
    public Queue createGetRecord2PaymentQueue() {
        return new Queue("getRecord2Payment", false);
    }

    @Bean
    public Exchange createGetRecord2PaymentExchange()
    {
        return new DirectExchange("getRecord2Payment");
    }

    @Bean
    public Binding bindingGetRecord2Payment(Queue createGetRecord2PaymentQueue, Exchange createGetRecord2PaymentExchange)
    {
        return BindingBuilder.bind(createGetRecord2PaymentQueue)
                .to(createGetRecord2PaymentExchange)
                .with("getRecord2Payment")
                .noargs();
    }
    @Bean
    public Queue createReturnMedicine2MedicineQueue() {
        return new Queue("returnMedicine2Medicine", false);
    }

    @Bean
    public Exchange createReturnMedicine2MedicineExchange()
    {
        return new DirectExchange("returnMedicine2Medicine");
    }

    @Bean
    public Binding bindingReturnMedicine2Medicine(Queue createReturnMedicine2MedicineQueue, Exchange createReturnMedicine2MedicineExchange)
    {
        return BindingBuilder.bind(createReturnMedicine2MedicineQueue)
                .to(createReturnMedicine2MedicineExchange)
                .with("returnMedicine2Medicine")
                .noargs();
    }
}
