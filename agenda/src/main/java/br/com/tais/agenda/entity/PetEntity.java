package br.com.tais.agenda.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pets")
public class PetEntity implements Serializable {

    @Id
    private Long petId;
    private String petName;
    private String petSpecies;
    private String tutorName;
    private String tutorEmail;
    private String petRace;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate petBirth;
    private Double petWeight;
    private String petColor;
    private String petDescription;
    private String imgUrl;

    public PetEntity() {

    }

    public PetEntity(Long petId, String petName, String petSpecies, String tutorName, String tutorEmail, String petRace, LocalDate petBirth, Double petWeight, String petColor, String petDescription, String imgUrl) {
        this.petId = petId;
        this.petName = petName;
        this.petSpecies = petSpecies;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
        this.petRace = petRace;
        this.petBirth = petBirth;
        this.petWeight = petWeight;
        this.petColor = petColor;
        this.petDescription = petDescription;
        this.imgUrl = imgUrl;
    }
}
