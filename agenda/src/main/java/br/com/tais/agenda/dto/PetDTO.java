package br.com.tais.agenda.dto;

import br.com.tais.agenda.entity.PetEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetDTO {


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

    public PetDTO() {
    }

    public PetDTO(String petName, String petSpecies, String tutorName, String tutorEmail, String petRace, LocalDate petBirth, Double petWeight, String petColor, String petDescription, String imgUrl) {

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

    public PetDTO(PetEntity petEntity) {

        this.petName = petEntity.getPetName();
        this.petSpecies = petEntity.getPetSpecies();
        this.tutorName = petEntity.getTutorName();
        this.tutorEmail = petEntity.getTutorEmail();
        this.petRace = petEntity.getPetRace();
        this.petBirth = petEntity.getPetBirth();
        this.petWeight = petEntity.getPetWeight();
        this.petColor = petEntity.getPetColor();
        this.petDescription = petEntity.getPetDescription();
        this.imgUrl = petEntity.getImgUrl();
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "petName='" + petName + '\'' +
                ", petSpecies='" + petSpecies + '\'' +
                ", tutorName='" + tutorName + '\'' +
                ", tutorEmail='" + tutorEmail + '\'' +
                ", petRace='" + petRace + '\'' +
                ", petBirth=" + petBirth +
                ", petWeight=" + petWeight +
                ", petColor='" + petColor + '\'' +
                ", petDescription='" + petDescription + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
