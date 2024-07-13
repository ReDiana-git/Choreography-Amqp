package nl.nl0e0.appointmentamqp.service;


import nl.nl0e0.petclinicentity.appointment.MedicalRecord;

public class SuccessActionAlert {
    public void createAppointmentAlert(MedicalRecord medicalRecord){
        System.out.println("-----------------");
        System.out.println("Create Appointment Success.");
        System.out.println(medicalRecord);
        System.out.println("-----------------");
    }
    public void deleteAppointmentsAlert(){
        System.out.println("-----------------");
        System.out.println("All Data in Database Has Been Deleted.");
        System.out.println("-----------------");
    }
}
