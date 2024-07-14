package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send a message when event occurs"
    label "send_message_to_consultation"

    input {
        triggeredBy("createAppointmentTrigger()")
    }

    outputMessage {
        sentTo "createConsultation"
        body([
                ownerId: 1,
                petId: 2,
                vetId: 1,
                id: $(anyNonBlankString()),
                appointmentId: $(anyNonBlankString()),
                consultationId: $(anyNonBlankString()),
                paymentId: $(anyNonBlankString()),
                medicineId: $(anyNonBlankString())
        ])
    }

}