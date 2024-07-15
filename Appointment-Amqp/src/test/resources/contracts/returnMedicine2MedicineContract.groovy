import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send a message when event occurs"
    label "send_message_to_payment"

    input {
        triggeredBy("returnMedicine2MedicineTriger()")
    }

    outputMessage {
        sentTo "returnMedicine2Medicine"
        body([
                ownerId: 1,
                petId: 1,
                vetId: 1,
                id: $(anyNonBlankString()),
                appointmentId: $(anyNonBlankString()),
                consultationId: $(anyNonBlankString()),
                paymentId: $(anyNonBlankString()),
                medicineId: $(anyNonBlankString())
        ])
    }
}