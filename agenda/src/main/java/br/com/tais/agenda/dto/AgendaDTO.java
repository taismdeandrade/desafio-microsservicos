package br.com.tais.agenda.dto;

import br.com.tais.agenda.entity.AgendaEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class AgendaDTO {

    private Long petId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    private String serviceType;

    public AgendaDTO() {
    }

    public AgendaDTO(Long petId, LocalDate appointmentDate, String serviceType) {
        this.petId = petId;
        this.appointmentDate = appointmentDate;
        this.serviceType = serviceType;
    }

    public AgendaDTO(AgendaEntity agendaEntity){
        this.petId = agendaEntity.getPetId();
        this.appointmentDate = agendaEntity.getAppointmentDate();
        this.serviceType = agendaEntity.getServiceType();
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
