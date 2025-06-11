package br.com.tais.notificacao.notificacao.entity;


import br.com.tais.notificacao.notificacao.dto.AgendaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class AgendaEntity implements Serializable {

    private Long appointmentId;
    private Long petId;
    private String petName;
    private String tutorName;
    private String tutorEmail;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    private String serviceType;

    public AgendaEntity() {
    }

    public AgendaEntity(Long appointmentId, Long petId, String petName, String tutorName, String tutorEmail, LocalDate appointmentDate, String serviceType) {
        this.appointmentId = appointmentId;
        this.petId = petId;
        this.petName = petName;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.appointmentDate = appointmentDate;
        this.serviceType = serviceType;
    }

    public AgendaEntity(AgendaDTO agendaDTO) {
        this.petId = agendaDTO.getPetId();
        this.petName = agendaDTO.getPetName();
        this.tutorName = agendaDTO.getTutorName();
        this.tutorEmail = agendaDTO.getTutorEmail();
        this.appointmentDate = agendaDTO.getAppointmentDate();
        this.serviceType = agendaDTO.getServiceType();
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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
