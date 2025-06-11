package br.com.tais.notificacao.notificacao.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Email implements Serializable {

    private String petName;
    private String tutorName;
    private LocalDate appointmentDate;

    public Email() {
    }

    public Email(String petName, String tutorName, LocalDate appointmentDate) {
        this.petName = petName;
        this.tutorName = tutorName;
        this.appointmentDate = appointmentDate;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
