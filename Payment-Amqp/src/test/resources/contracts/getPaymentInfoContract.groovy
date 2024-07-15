package contracts

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should send a message when event occurs"
    label "getPaymentInfo"

    input {
        triggeredBy("getPaymentInfoTrigger()")
    }

    outputMessage {
        sentTo "getRecord2Payment"
        body("ee9a168b-b9ae-42ed-8b4c-0f06148574d0")
    }
}



