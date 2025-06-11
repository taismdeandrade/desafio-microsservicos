package br.com.tais.notificacao.notificacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AgendaDTO {

    private String petName;
    private Long petId;
    private String tutorName;
    private String tutorEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    private String serviceType;

    public AgendaDTO() {
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public AgendaDTO(String petName, Long petId, String tutorName, String tutorEmail, LocalDate appointmentDate, String serviceType) {
        this.petName = petName;
        this.petId = petId;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.appointmentDate = appointmentDate;
        this.serviceType = serviceType;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
